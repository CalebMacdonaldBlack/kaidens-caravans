(ns kaidens-caravans.components.page
  (:require [kaidens-caravans.components.nav :refer [nav]]
            [kaidens-caravans.components.home.page :as home-page]
            [kaidens-caravans.components.caravan.page :as caravan-page]
            [kaidens-caravans.components.new-caravan.page :as new-caravan-page]
            [re-frame.core :as rf]))

(defn pages []
  {:home #'home-page/view
   :caravans #'caravan-page/view
   :new-caravan #'new-caravan-page/view})

(defn page-base []
  [:div
   [nav]
   [((pages) @(rf/subscribe [:page]))]])

