(ns kaidens-caravans.core-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [pjstadig.humane-test-output]
            [reagent.core :as reagent :refer [atom]]
            [kaidens-caravans.core :as rc]))

(deftest test-home
  (is (= true true)))

