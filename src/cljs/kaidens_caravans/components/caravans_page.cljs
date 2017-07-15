(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:set-active-page :caravans]))

(defn form-input [name key size type ratom]
  [:div.form-group.row
   [:label.col-2.col-form-label name]
   [:div.col-10>input.form-control {:type type
                                    :on-change #(swap! ratom assoc key (.-target.value %))
                                    :value (key @ratom)}]])

(defn modify-caravan-form [caravan]
  [:div
   [form-input "Make" :make 3 "text" caravan]
   [form-input "Model" :model 3 "text" caravan]
   [form-input "Type" :type 2 "text" caravan]
   [form-input "VIN Number" :vin 2 "text" caravan]
   [form-input "Frame" :frame 2 "text" caravan]
   [form-input "Terrain" :terrain 2 "text" caravan]
   [form-input "Length (feet)" :length 2 "number" caravan]
   [form-input "Weight (Tonne)" :weight 2 "number" caravan]
   [form-input "Year" :year 2 "number" caravan]
   [form-input "Axles" :axles 2 "text" caravan]
   [form-input "Condition" :condition 2 "text" caravan]
   [form-input "Bed" :bed 2 "text" caravan]
   [form-input "Fridge" :fridge 2 "text" caravan]
   [form-input "Suspension" :suspension 2 "text" caravan]
   [form-input "price" :price 2 "number" caravan]])

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

