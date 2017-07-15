(ns kaidens-caravans.subscriptions
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
  :page
  (fn [db _]
    (:page db)))

(reg-sub
  :current-caravan
  (fn [db _]
    (:current-caravan db)))
