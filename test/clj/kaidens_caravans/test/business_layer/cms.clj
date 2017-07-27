(ns kaidens-caravans.test.business-layer.cms
    (:require [clojure.test :refer :all]))

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
