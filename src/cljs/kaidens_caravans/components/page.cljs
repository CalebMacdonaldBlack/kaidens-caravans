(ns kaidens-caravans.components.page
  (:require [kaidens-caravans.components.nav :refer [nav]]
            [kaidens-caravans.components.home-page :refer [home-page]]
            [kaidens-caravans.components.caravans-page :refer [caravans-page]]
            [re-frame.core :as rf]))

(def pages
  {:home #'home-page
   :caravans #'caravans-page})

(defn page-base []
  [:div
   [nav]
   [:div ((@(rf/subscribe [:page]) pages))]])

