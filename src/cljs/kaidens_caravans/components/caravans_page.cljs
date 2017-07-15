(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:set-active-page :caravans]))

(defn form-input [name key type ratom]
  [:div.form-group.row
   [:label.col-2.col-form-label name]
   [:div.col-10>input.form-control {:type type
                                    :on-change #(swap! ratom assoc key (.-target.value %))
                                    :value (key @ratom)}]])

(defn modify-caravan-form [caravan]
  [:div
   [form-input "Make" :make"text" caravan]
   [form-input "Model" :model"text" caravan]
   [form-input "Type" :type"text" caravan]
   [form-input "VIN Number" :vin "text" caravan]
   [form-input "Frame" :frame "text" caravan]
   [form-input "Terrain" :terrain "text" caravan]
   [form-input "Length (feet)" :feet "number" caravan]
   [form-input "Weight (Tonne)" :tonne "number" caravan]
   [form-input "Year" :year "number" caravan]
   [form-input "Axles" :axles "text" caravan]
   [form-input "Condition" :condition "text" caravan]
   [form-input "Bed" :bed "text" caravan]
   [form-input "Fridge" :fridge "text" caravan]
   [form-input "Suspension" :suspension "text" caravan]
   [form-input "price" :price "number" caravan]])

(defn caravans-page []
  (let [caravan @(rf/subscribe [:current-caravan])]
    [:div.container
     [:br]
     [:h1 "Caravans"]
     [:div.card.card-block
      [:h2 "New Caravan"]
      [:br]
      [modify-caravan-form caravan]
      [:br]
      [:button.btn.btn-primary {:type "button" :on-click #(rf/dispatch [:create-caravan @caravan])} "Add New Caravan"]]]))

