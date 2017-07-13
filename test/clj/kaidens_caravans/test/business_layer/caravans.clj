(ns kaidens-caravans.test.business-layer.caravans
    (:require [clojure.test :refer :all]
              [kaidens-caravans.business-layer.caravans :refer [create!]]))

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
                   :video ["http://vid.com/fakevideo.mp4"]})

(deftest test-caravans
  (testing "create-caravan"
    (with-redefs [kaidens-caravans.db.core/create-caravan! (mock-create-caravan mock-caravan "works")]
      (is (= (create! mock-caravan) "works")))))