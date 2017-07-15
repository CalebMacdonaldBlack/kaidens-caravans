(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:set-active-page :caravans]))

(defn create-caravan []
  [:div
   [:grid
    [:div {:local "1/2"}
     [:label "Make: "]
     [:input {:type "text" :placeholder "Make"}]]
    [:div {:local "1/2"}
     [:label "Make: "]
     [:input {:type "text" :placeholder "Make"}]]]])

(defn caravans-page []
  [:section
   [:h1 "Caravans"]
   [create-caravan]])
