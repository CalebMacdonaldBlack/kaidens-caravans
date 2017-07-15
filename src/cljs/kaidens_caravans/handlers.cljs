(ns kaidens-caravans.handlers
  (:require [kaidens-caravans.db :as db]
            [kaidens-caravans.ajax :refer [post-json]]
            [re-frame.core :as rf]
            [re-frame.core :refer [dispatch reg-event-db reg-event-fx]]
            [ajax.core :as ajax]))

(defn- initialize-db [_ _] db/default-db)
(reg-event-db :initialize-db initialize-db)

(defn- set-active-page [db [_ page]] (assoc db :page page))
(reg-event-db :set-active-page set-active-page)

(defn- clear-current-caravan [{:keys [db]} _] (reset! (:current-caravan db) {}) {})
(reg-event-fx :clear-current-caravan clear-current-caravan)

(defn- create-caravan [_ [_ caravan]]
  (post-json {:url "/caravans"
              :body caravan
              :after-success (rf/dispatch [:clear-current-caravan])}))
(reg-event-fx :create-caravan create-caravan)
