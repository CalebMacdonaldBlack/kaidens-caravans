(ns kaidens-caravans.test.subscriptions
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [pjstadig.humane-test-output]
            [reagent.core :as reagent :refer [atom]]
            [kaidens-caravans.subscriptions :as s]))

(deftest subscribers
  (testing "page"
    (let [db {:page :home}
          page #'s/page]
      (is (= (page db) :home))))

  (testing "current-caravan"
    (let [db {:current-caravan "testvalue"}
          current-caravan #'s/current-caravan]
      (is (= (current-caravan db) "testvalue"))))

  (testing "caravans"
    (let [db {:caravans "testvalue"}
          caravans #'s/caravans]
      (is (= (caravans db) "testvalue"))))

  (testing "hide-disabled"
    (let [db {:hide-disabled true}
          hide-disabled #'s/hide-disabled]
      (is (= (hide-disabled db) true)))))
