(ns kaidens-caravans.business-layer.cms
  (:require [clojure.java.io :as io]
            [kaidens-caravans.config :refer [env]]
            [amazonica.aws.s3 :as s3]
            [ring.util.http-response :as response]
            [kaidens-caravans.db.core :refer :all]
            [clojure.string :as string]
            [clojure.string :as s]))

(defn- s3-put!
  [stream bytes content-type file-name]
  (s3/put-object :endpoint (env :aws-region)
                 :bucket-name (env :bucket-name)
                 :key file-name
                 :input-stream stream
                 :meta-data {:content-length bytes
                             :content-type content-type}
                 :return-values "ALL_OLD"))

(defn- upload-layout!
  [template-name new-name params]
  (clojure.tools.logging/debug "Uploading: " new-name)
  (let [bytes (.getBytes (.toString (kaidens-caravans.layout/render-html template-name params)) "UTF-8")
        stream (java.io.ByteArrayInputStream. bytes)]
    (s3-put! stream (count bytes) "text/html" new-name)))

(defn- file-content-type
  [file-name]
  (case (last (s/split file-name #"\."))
    "css" "text/css"
    "js" "text/javascript"
    "jpg" "image/jpeg"
    "png" "image/png"
    ""))

(defn- upload-file!
  [file file-path content-type]
  (clojure.tools.logging/debug "Uploading: " file-path)
  (let [s (io/input-stream file)
        b (.available s)]
    (s3-put! s b content-type file-path)))

(defn- upload-static-files!
  []
  (doseq [file (file-seq (io/file "./resources/public"))]
    (let [file-path (str (.getPath file))
          content-type (file-content-type file-path)]
      (if (not (empty? content-type))
        (upload-file! file (s/replace file-path #"\./resources/public/" "") content-type)))))

(defn- upload-dynamic-files!
  []
  (let [caravans (filter #(not (:archived %)) (retrieve-caravans))]
    (upload-layout! "stock.html" "stock.html" {:stock (map (fn [m] (vector (first m) (second m))) (group-by :type caravans))})
    (doseq [caravan caravans]
      (upload-layout! "singlestock.html" (str (:id caravan) ".html") caravan))
    (upload-layout! "caravan.html" "caravan.html" {})
    (upload-layout! "single_caravan.html" "single_caravan.html" {})
    (upload-layout! "homepage.html" "index.html" {})))

(defn upload! []
  (upload-static-files!)
  (upload-dynamic-files!)
  (clojure.tools.logging/debug "Upload Complete!")
  (response/created (str "http://" (env :bucket-name) ".s3-website-ap-southeast-2.amazonaws.com")))

