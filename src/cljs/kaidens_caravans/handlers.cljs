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
    ;(reset! (:current-caravan db) {})
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


    ;(ajax/ajax-request
    ;  {:uri "/caravans"
    ;   :method :post
    ;   :params {:message "hello world"
    ;            :user "bob"}
    ;   :handler #(cljs.pprint/pprint %)
    ;   :format (ajax/json-request-format)
    ;   :response-format (ajax/json-response-format {:keywords? true})})
    ;{}))

    ;{:http-xhrio {:method          :post
    ;              :uri             "/caravans"
    ;              :params          caravan
    ;              :timeout         5000
    ;              :format          (ajax/json-request-format)
    ;              :response-format (ajax/json-response-format {:keywords? true})
    ;              :on-success      [::good-post-result]
    ;              :on-failure      [::bad-post-result]}}))

