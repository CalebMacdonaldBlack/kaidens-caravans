(ns kaidens-caravans.components.page
  (:require [kaidens-caravans.components.nav :refer [nav]]
            [kaidens-caravans.components.home-page :refer [home-page]]
            [kaidens-caravans.components.caravan.page :refer [page]]
            [re-frame.core :as rf]))

(defn pages []
  {:home #'home-page
   :caravans #'page})

(defn page-base []
  [:div
   [nav]
   [((pages) @(rf/subscribe [:page]))]])

