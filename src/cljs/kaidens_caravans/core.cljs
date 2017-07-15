(ns kaidens-caravans.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [day8.re-frame.http-fx]
            [goog.events :as events]
            [goog.history.EventType :as HistoryEventType]
            [markdown.core :refer [md->html]]
            [ajax.core :refer [GET POST]]
            [kaidens-caravans.ajax :refer [load-interceptors!]]
            [kaidens-caravans.components.page :refer [page-base]]
            [kaidens-caravans.handlers]
            [kaidens-caravans.subscriptions]
            [secretary.core :as secretary]
            [accountant.core :as accountant])
  (:import goog.History))

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      HistoryEventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; Routes
(accountant/configure-navigation! {:nav-handler  (fn [path] (secretary/dispatch! path))
                                   :path-exists? (fn [path] (secretary/locate-route path))})

(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
                    (rf/dispatch [:set-active-page :home]))

;; -------------------------
;; Initialize app

(defn mount-components []
  (rf/clear-subscription-cache!)
  (r/render [#'kaidens-caravans.components.page/page-base] (.getElementById js/document "app")))

(defn init! []
  (rf/dispatch-sync [:initialize-db])
  (load-interceptors!)
  (hook-browser-navigation!)
  (mount-components))
