(ns kaidens-caravans.handlers
  (:require [kaidens-caravans.db :as db]
            [kaidens-caravans.ajax :refer [post-json get-json put-json]]
            [re-frame.core :as rf]
            [re-frame.core :refer [dispatch reg-event-db reg-event-fx reg-fx]]
            [ajax.core :as ajax]
            [accountant.core :as accountant]))

(defonce timeouts
         (atom {}))

(reg-fx :dispatch-debounce
        (fn [[id event-vec n]]
          (js/clearTimeout (@timeouts id))
          (swap! timeouts assoc id
                 (js/setTimeout (fn []
                                  (dispatch event-vec)
                                  (swap! timeouts dissoc id))
                                n))))

(defn- initialize-db [_ _] db/default-db)
(reg-event-db :initialize-db initialize-db)

(defn- set-active-page [db [_ page]] (assoc db :page page))
(reg-event-db :set-active-page set-active-page)

(defn- clear-current-caravan [{:keys [db]} _] (reset! (:current-caravan db) {}) {})
(reg-event-fx :clear-current-caravan clear-current-caravan)

(defn- create-caravan [_ [_ caravan]]
  (post-json {:url           "/caravans"
              :body          caravan
              :after-success [[:clear-current-caravan] [:load-caravans]]}))
(reg-event-fx :create-caravan create-caravan)

(defn- set-caravans [{:keys [db]} [_ caravans]]
  {:db (assoc db :caravans caravans)})
(reg-event-fx :set-caravans set-caravans)

(defn- load-caravans [{:keys [db]} _]
  (get-json {:url           (if (:hide-disabled db) "/caravans?archived=false" "/caravans")
             :after-success [[:set-caravans]]}))
(reg-event-fx :load-caravans load-caravans)

(defn- edit-caravan [{:keys [db]} [_ caravan]]
  (put-json {:url           (str "/caravans/" (:id caravan))
             :body          caravan
             :after-success [[:load-caravans] [:navigate "#/caravans"]]}))
(reg-event-fx :edit-caravan edit-caravan)

(defn- set-hide-disabled [{:keys [db]} [_ value]]
  {:db       (assoc db :hide-disabled value)
   :dispatch [:load-caravans]})
(reg-event-fx :set-hide-disabled set-hide-disabled)

(reg-event-fx
  :set-search-list
  (fn [_ [_ list-ratom id result]]
    (reset! list-ratom result)
    (.addClass (js/jQuery (str "#" id)) "show")
    {}))


(reg-event-fx
  :do-search
  (fn [_ [_ endpoint list-ratom id]]
    (get-json {:url           endpoint
               :after-success [[:set-search-list list-ratom id]]})))

(reg-event-fx
  :search-field-updated
  (fn [_ [_ endpoint list-ratom id]]
    {:dispatch-debounce [::search [:do-search endpoint list-ratom id] 250]}))

(reg-event-fx
  :select-current-caravan
  (fn [{:keys [db]} [_ caravan]]
    (reset! (:current-caravan db) caravan)
    {:dispatch [:navigate "#/new-caravan"]}))

(reg-event-fx
  :navigate
  (fn [_ [_ route]]
    (accountant/navigate! route)))

