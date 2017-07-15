(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:set-active-page :caravans]))

(defn create-caravan []
  [:div.row.well
   [:h2.col-md-12 "Add New Caravan"]
   [:div.col-md-12
    [:br]]
   [:div.col-md-2.form-group
    [:label "Type"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-3.form-group
    [:label "Make"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-3.form-group
    [:label "Model"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "VIN Number"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Frame"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Terrain"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Length (Feet)"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Weight (Tonne)"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Year"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Axels"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Condition"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Bed"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-3.form-group
    [:label "Fridge"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Suspension"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-2.form-group
    [:label "Price"]
    [:input.form-control {:type "text"}]]
   [:div.col-md-12
    [:br]
    [:button.btn.btn-success {:type "button"} "Add Caravan"]]])


(defn caravans-page []
  [:div.container
   [:h1 "Caravans"]
   [create-caravan]])
