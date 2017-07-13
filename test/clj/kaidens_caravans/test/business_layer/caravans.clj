(ns kaidens-caravans.test.business-layer.caravans
    (:require [clojure.test :refer :all]
              [kaidens-caravans.business-layer.caravans :refer [create!]]
              [kaidens-caravans.db.core :refer [create-caravan!]]
              [kaidens-caravans.test.util :refer :all]))

(defn mock-create-caravan [expected return-value]
  (fn [caravan]
    (is (= expected caravan))
    return-value))

(def mock-caravan {:type "Caravan"
                   :make "Road Star"
                   :model "Offroad"
                   :year 1998
                   :feet 21
                   :tonne 2.5
                   :features ["shower" "toilet"]
                   :photos ["http://img.com/fakeimage.jpg"]
                   :videos ["http://vid.com/fakevideo.mp4"]})

(deftest test-caravans
  (testing "create-caravan"
    (with-redefs [create-caravan! (mock-create-caravan mock-caravan 1)]
      (let [{:keys [status body]} (create! {:body-params mock-caravan})]
        (is (= status 200))
        (is (= {:rows-affected 1} body))))))