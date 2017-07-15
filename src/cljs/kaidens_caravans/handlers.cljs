(ns kaidens-caravans.handlers
  (:require [kaidens-caravans.db :as db]
            [kaidens-caravans.ajax :refer [post-json]]
            [re-frame.core :as rf]
            [re-frame.core :refer [dispatch reg-event-db reg-event-fx]]
            [ajax.core :as ajax]))

(reg-event-db
  :initialize-db
  (fn [_ _]
    db/default-db))

(reg-event-db
  :set-active-page
  (fn [db [_ page]]
    (assoc db :page page)))

(reg-event-fx
  :clear-current-caravan
  (fn [{:keys [db]} _]
    (reset! (:current-caravan db) {})
    {}))

(reg-event-fx
  ::success-create-caravan
  (fn [_ [_ result]]
    (cljs.pprint/pprint result)))

(reg-event-fx
  ::failure-create-caravan
  (fn [_ [_ result]]
    (cljs.pprint/pprint result)))

(reg-event-db
  :good-post-result
  (fn [db [_ result]]
    (assoc db :api-result result)))

(reg-event-db
  :bad-post-result
  (fn [db [_ result]]
    (assoc db :api-result result)))

(reg-event-fx
  :create-caravan
  (fn [_ [_ caravan]]
    (post-json {:url "/caravans"
                :body caravan
                :after-success (rf/dispatch [:clear-current-caravan])})))
