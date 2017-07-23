(ns kaidens-caravans.business-layer.cms
  (:require [clojure.java.io :as io]
            [kaidens-caravans.config :refer [env]]
            [amazonica.aws.s3 :as s3]
            [ring.util.http-response :as response]
            [kaidens-caravans.db.core :refer :all]
            [clojure.string :as string]))

(defn- s3-put
  [stream bytes content-type file-name]
  (s3/put-object {:endpoint (env :aws-region)}
                 :bucket-name (env :bucket-name)
                 :key file-name
                 :input-stream stream
                 :meta-data {:content-length bytes
                             :content-type content-type}
                 :return-values "ALL_OLD"))

(defn- upload-page
  [file-name content-type]
  (let [s (io/input-stream (io/file (str "resources/public/" file-name)))
        b (.available s)]
    (s3-put s b content-type file-name)))

(defn- upload-layout
  [template-name new-name params]
  (let [bytes (.getBytes (.toString (kaidens-caravans.layout/render-html template-name params)) "UTF-8")
        stream (java.io.ByteArrayInputStream. bytes)]
    (s3-put stream (count bytes) "text/html" new-name)))

(def files
  [["css/custom.css" "text/css"]
   ["css/material.css" "text/css"]
   ["css/website_custom.css" "text/css"]
   ["js/material.js" "text/javascript"]
   ["img/banner.jpg" "image/jpeg"]])

(defn upload! []
  (doseq [file files]
    (upload-page (first file) (second file)))
  (let [caravans (retrieve-caravans)]
    (upload-layout "stock.html" "stock.html" {:stock (map (fn [m] (vector (first m) (second m))) (group-by :type caravans))})
    (doseq [caravan caravans]
      (upload-layout "singlestock.html" (str (:id caravan) ".html") caravan)))

  (upload-layout "caravan.html" "caravan.html" {})
  (upload-layout "single_caravan.html" "single_caravan.html" {})
  (upload-layout "homepage.html" "index.html" {})
  (response/ok "DONE"))
  ;(upload-page (slurp "resources/public/css/custom.css") "css/custom.css" "text/css")
  ;(upload-page (slurp "resources/public/css/material.css") "css/material.css" "text/css")
  ;(upload-page (slurp "resources/public/css/website_custom.css") "css/website_custom.css" "text/css")
  ;(response/ok (upload-page (.toString (kaidens-caravans.layout/render-html "homepage.html")) "index.html" "text/html")))

