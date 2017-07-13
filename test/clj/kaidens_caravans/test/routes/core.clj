(ns kaidens-caravans.test.routes.core
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [kaidens-caravans.handler :refer :all]
            [kaidens-caravans.test.util :refer :all]
            [kaidens-caravans.business-layer.caravans :as caravans]
            [ring.util.http-response :as response]))

(deftest caravan-routes
  (testing "Create caravan route"
    (with-redefs [caravans/create! (constantly (response/ok {:caravans-create! "called"}))]
      (let [{:keys [status body]} ((app) (json-request :post "/caravans" nil))]
        (is (= status 200))
        (is (= {:caravans-create! "called"} (parse-body body))))))

  (testing "Retrieve caravans route"
    (with-redefs [caravans/retrieve (constantly (response/ok {:caravans-retrieve "called"}))]
      (let [{:keys [status body]} ((app) (request :get "/caravans" nil))]
        (is (= status 200))
        (is (= {:caravans-retrieve "called"} (parse-body body)))))))