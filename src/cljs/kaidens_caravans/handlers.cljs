(ns kaidens-caravans.handlers
  (:require [kaidens-caravans.db :as db]
            [kaidens-caravans.ajax :refer [post-json get-json put-json]]
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
              :after-success [[:clear-current-caravan] [:load-caravans] [:hide-modal "#caravanModal"]]}))
(reg-event-fx :create-caravan create-caravan)

(defn- set-caravans [{:keys [db]} [_ caravans]]
  {:db (assoc db :caravans caravans)})
(reg-event-fx :set-caravans set-caravans)

(defn- load-caravans [{:keys [db]} _]
  (get-json {:url (if (:hide-disabled db) "/caravans?archived=false" "/caravans")
             :after-success [[:set-caravans]]}))
(reg-event-fx :load-caravans load-caravans)

(defn- hide-modal [_ [_ id]]
  (.modal (js/jQuery id) "hide")
  {})
(reg-event-fx :hide-modal hide-modal)

(defn- edit-caravan [{:keys [db]} [_ caravan]]
  (put-json {:url (str "/caravans/" (:id caravan))
             :body caravan
             :after-success [[:load-caravans] [:hide-modal "#caravanModal"]]}))
(reg-event-fx :edit-caravan edit-caravan)

(defn- set-hide-disabled [{:keys [db]} [_ value]]
  {:db (assoc db :hide-disabled value)
   :dispatch [:load-caravans]})
(reg-event-fx :set-hide-disabled set-hide-disabled)
