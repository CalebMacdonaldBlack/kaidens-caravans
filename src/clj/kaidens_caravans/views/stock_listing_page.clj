(ns kaidens-caravans.views.stock-listing-page
  (:require [hiccup.core :refer :all]
            [kaidens-caravans.views.footer :as footer]
            [kaidens-caravans.views.head-body :as head-body]
            [kaidens-caravans.views.header :as header]
            [kaidens-caravans.views.stock-listing :as stock-listing]
            [kaidens-caravans.views.nav :as nav]
            [kaidens-caravans.db.core :refer :all]))

(defn view []
  (head-body/view
    (header/view)
    (nav/view)
    (stock-listing/view (filter #(and (not (:archived %)) (= "Caravan" (:type %))) (retrieve-caravans)) "Caravans")
    (footer/view)))
