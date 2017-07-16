(ns kaidens-caravans.test.handler
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [kaidens-caravans.ajax :refer [post-json get-json]]
            [pjstadig.humane-test-output]
            [reagent.core :as r]
            [kaidens-caravans.handlers :as h]))

(deftest handlers

  (testing "initialize-db"
    (let [initialized-db kaidens-caravans.db/default-db
          initialize-db #'h/initialize-db]
      (is (= initialized-db (initialize-db nil nil)))))

  (testing "set-active-page"
    (let [set-active-page #'h/set-active-page]
      (is (= {:page :home} (set-active-page {} [nil :home])))))

  (testing "clear-current-caravan"
    (let [clear-current-caravan #'h/clear-current-caravan
          current-caravan (r/atom {:test "test"})
          cofx {:db {:current-caravan current-caravan}}]
      (is (= {} (clear-current-caravan cofx nil)))
      (is (= {} @current-caravan))))

  (testing "create-caravan"
    (with-redefs [post-json (constantly "called")]
      (let [create-caravan #'h/create-caravan]
        (is (= "called" (create-caravan {:test "hello"}))))))

  (testing "set-caravans"
    (let [set-caravans #'h/set-caravans]
      (is (= {:db {:caravans "testvalue"}} (set-caravans {} [nil "testvalue"])))))

  (testing "create-caravan"
    (with-redefs [get-json (constantly "called")]
      (let [load-caravan #'h/load-caravans]
        (is (= "called" (load-caravan {:test "hello"})))))))
