(ns kaidens-caravans.components.caravan.caravan-modal
  (:require [re-frame.core :as rf]))

(defn- form-input [name key type ratom]
  [:div.form-group.row
   [:label.col-3.col-form-label name]
   [:div.col-9>input.form-control {:type      type
                                    :on-change #(swap! ratom assoc key (.-target.value %))
                                    :value     (key @ratom)}]])

(defn form-select [name key options ratom coerce]
  [:div.form-group.row
   [:label.col-3.col-form-label name]
   [:div.col-9>select.form-control {:on-change #(swap! ratom assoc key (coerce (.-target.value %)))
                                    :value (if (key @ratom) "true" "false")}
    (for [[label value] options]
      ^{:key (str name value)}
      [:option {:value value} label])]])

(defn- modify-caravan-form [caravan]
  [:div
   (when (:archived @caravan)
     [:div.alert.alert-warning {:role "alert"}
      [:i.fa.fa-warning]
      " This caravan is disabled and will not show up on the website."])
   [form-select "Status" :archived [["Enabled" false] ["Disabled" true]] caravan #(= "true" %)]
   [form-input "Make" :make "text" caravan]
   [form-input "Model" :model "text" caravan]
   [form-input "Type" :type "text" caravan]
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

(defn caravan-modal [caravan]
  (let [new? (:id @caravan)
        title (if new? "Edit Caravan" "New Caravan")
        action (if new? #(rf/dispatch [:edit-caravan @caravan]) #(rf/dispatch [:create-caravan @caravan]))]
    [:div#caravanModal.modal.fade {:aria-labelledby "caravanModalLabel" :aria-hidden "true" :role "dialog"}
     [:div.modal-dialog.modal-lg {:role "document"}
      [:div.modal-content
       [:div.modal-header
        [:h5#caravanModalLabel.modal-title title]
        [:button.close {:type "button" :data-dismiss "modal" :aria-label "Close"}
         [:i.fa.fa-times {:aria-hidden "true"}]]]
       [:div.modal-body
        [modify-caravan-form caravan]]
       [:div.modal-footer
        [:button.btn.btn-secondary {:type "button" :data-dismiss "modal"} "Close"]
        [:button.btn.btn-primary {:type "button" :on-click action} "Save Changes"]]]]]))

