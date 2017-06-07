(ns kaidens-caravans.components.page
  (:require [kaidens-caravans.components.nav :refer [nav]]
            [kaidens-caravans.components.home-page :refer [home-page]]
            [re-frame.core :as rf]))

(def pages
  {:home #'home-page})

(defn page []
  [:div
   [nav]
   [(pages @(rf/subscribe [:page]))]])

