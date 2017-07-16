(ns kaidens-caravans.subscriptions
  (:require [re-frame.core :refer [reg-sub]]))

(defn- page [db _] (:page db))
(reg-sub :page page)

(defn- current-caravan [db _] (:current-caravan db))
(reg-sub :current-caravan current-caravan)

(defn- caravans [db _] (:caravans db))
(reg-sub :caravans caravans)

(defn- hide-disabled [db _] (:hide-disabled db))
(reg-sub :hide-disabled hide-disabled)
