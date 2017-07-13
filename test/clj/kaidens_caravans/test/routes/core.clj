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
        (is (= {:caravans-retrieve "called"} (parse-body body))))))

  (testing "Update caravans route"
    (with-redefs [caravans/update! (constantly (response/ok {:caravans-update! "called"}))]
      (let [{:keys [status body]} ((app) (json-request :put "/caravans/12341234" nil))]
        (is (= status 200))
        (is (= {:caravans-update! "called"} (parse-body body))))))

  (testing "Delete caravan route"
    (let [id "12341234"]
      (with-redefs [caravans/delete! (fn [req]
                                        (is (= id (get-in req [:route-params :id])))
                                        (response/ok {:caravans-delete! "called"}))]
        (let [{:keys [status body]} ((app) (request :delete (str "/caravans/" id) nil))]
          (is (= status 200))
          (is (= {:caravans-delete! "called"} (parse-body body))))))))
