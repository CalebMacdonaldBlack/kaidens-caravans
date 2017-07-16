(ns kaidens-caravans.handlers
  (:require [kaidens-caravans.db :as db]
            [kaidens-caravans.ajax :refer [post-json get-json]]
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
              :after-success [[:clear-current-caravan] [:load-caravans]]}))
(reg-event-fx :create-caravan create-caravan)

(defn- set-caravans [{:keys [db]} [_ caravans]]
  {:db (assoc db :caravans caravans)})
(reg-event-fx :set-caravans set-caravans)

(defn- load-caravans [_ _]
  (get-json {:url "/caravans"
             :after-success [[:set-caravans]]}))
(reg-event-fx :load-caravans load-caravans)
