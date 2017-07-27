(ns kaidens-caravans.test.business-layer.cms
    (:require [clojure.test :refer :all]
              [kaidens-caravans.business-layer.cms :refer :all]))

(deftest cms-s3-put!-test
  (testing "that function is called with the correct map"
    (let [s3-put! #'kaidens-caravans.business-layer.cms/s3-put!]
      (with-redefs [kaidens-caravans.config/env {:aws-region "ap-southeast-2"
                                                 :bucket-name "kaidens-caravans"}
                    amazonica.aws.s3/put-object (fn [_ r _ b _ f _ s _ m _ rv]
                                                  (is (= "ap-southeast-2" r))
                                                  (is (= "kaidens-caravans" b))
                                                  (is (= "file-name" f))
                                                  (is (= "stream" s))
                                                  (is (= {:content-length "bytes"
                                                          :content-type "content-type"} m))
                                                  (is (= "ALL_OLD" rv)))]
        (s3-put! "stream" "bytes" "content-type" "file-name")))))

(deftest file-content-type-test
  (testing "that correct content type works"
    (let [file-content-type #'kaidens-caravans.business-layer.cms/file-content-type]
      (is (= "text/css" (file-content-type "test.file.css")))
      (is (= "text/javascript" (file-content-type "test.file.js")))
      (is (= "image/jpeg" (file-content-type "test.jpg")))
      (is (= "image/png" (file-content-type "thing.png")))
      (is (= "" (file-content-type "test.exe"))))))

(deftest cms-upload!-test
  (testing "that upload returns correct response"
    (with-redefs [kaidens-caravans.business-layer.cms/upload-static-files! (constantly "")
                  kaidens-caravans.business-layer.cms/upload-dynamic-files! (constantly "")
                  kaidens-caravans.config/env {:bucket-name "kaidens-caravans"}
                  ring.util.http-response/created (fn [url]
                                                    (is (= "http://kaidens-caravans.s3-website-ap-southeast-2.amazonaws.com" url)))]
      (upload!))))
