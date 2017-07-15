(ns kaidens-caravans.subscriptions
  (:require [re-frame.core :refer [reg-sub]]))

(defn- page [db _] (:page db))
(defn- current-caravan [db _] (:current-caravan db))

(reg-sub :page page)
(reg-sub :current-caravan current-caravan)
