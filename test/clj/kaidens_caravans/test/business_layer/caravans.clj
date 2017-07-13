(ns kaidens-caravans.test.business-layer.caravans
    (:require [clojure.test :refer :all]
              [kaidens-caravans.business-layer.caravans :refer :all]
              [kaidens-caravans.db.core :refer :all]
              [kaidens-caravans.test.util :refer :all]))

(defn mock-db [expected return-value]
  (fn [caravan]
    (is (= expected caravan))
    return-value))


(def mock-caravan {:id "69fc8418-74ed-421b-bcf3-c237f1901d8e"
                   :type "Caravan"
                   :make "Road Star"
                   :model "Offroad"
                   :price 40000
                   :year 1998
                   :feet 21
                   :tonne 2.5
                   :features ["shower" "toilet"]
                   :photos ["http://img.com/fakeimage.jpg"]
                   :videos ["http://vid.com/fakevideo.mp4"]})

(deftest test-caravans
  (testing "create-caravan"
    (with-redefs [create-caravan! (mock-db mock-caravan 1)]
      (let [{:keys [status body]} (create! {:body-params mock-caravan})]
        (is (= status 200))
        (is (= {:rows-affected 1} body)))))

  (testing "retrieve-caravans"
    (with-redefs [retrieve-caravans (constantly [mock-caravan])]
      (let [{:keys [status body]} (retrieve)]
        (is (= status 200))
        (is (= [mock-caravan] body)))))

  (testing "update-caravans"
    (with-redefs [update-caravan! (mock-db mock-caravan 1)]
      (let [{:keys [status body]} (update! {:body-params mock-caravan})]
        (is (= status 200))
        (is (= {:rows-affected 1} body)))))

  (testing "delete-caravan"
    (let [id {:id "69fc8418-74ed-421b-bcf3-c237f1901d8e"}]
      (with-redefs [delete-caravan! (mock-db id 1)]
        (let [{:keys [status body]} (delete! {:route-params id})]
          (is (= status 200))
          (is (= {:rows-affected 1} body)))))))
