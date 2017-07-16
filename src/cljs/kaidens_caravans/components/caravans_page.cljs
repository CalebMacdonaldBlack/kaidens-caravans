(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:load-caravans])
                    (rf/dispatch [:set-active-page :caravans]))

(defn form-input [name key type ratom]
  [:div.form-group.row
   [:label.col-2.col-form-label name]
   [:div.col-10>input.form-control {:type      type
                                    :on-change #(swap! ratom assoc key (.-target.value %))
                                    :value     (key @ratom)}]])

(defn modify-caravan-form [caravan]
  [:div
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
        action (if new? #(rf/dispatch [:create-caravan @caravan]) #(js/alert "Edit not implemented!"))]
    [:div#caravanModal.modal.fade {:tabindex "-1" :aria-labelledby "caravanModalLabel" :aria-hidden "true" :role "dialog"}
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
        [:button.btn.btn-primary {:type "button":on-click action}  "Save Changes"]]]]]))

(defn caravan-table-row [{:keys [vin make model type terrain feet tonne year price]}]
  [:tr
   [:td.text-right vin]
   [:td make]
   [:td model]
   [:td type]
   [:td terrain]
   [:td.text-right feet]
   [:td.text-right tonne]
   [:td.text-right year]
   [:td.text-right price]])

(defn caravan-table []
  (let [caravans @(rf/subscribe [:caravans])]
    [:div.col-10.offset-1
      [:table.table.table-striped
       [:thead
        [:tr
         [:th "VIN"]
         [:th "Make"]
         [:th "Model"]
         [:th "Type"]
         [:th "Terrain"]
         [:th "Length (feet)"]
         [:th "Weight (tonne)"]
         [:th "Year"]
         [:th "Price"]]]
       [:tbody
        (for [caravan caravans]
          [caravan-table-row caravan])]]]))

(defn caravans-page []
  (let [caravan @(rf/subscribe [:current-caravan])
        new-caravan-toggle (r/atom false)]
    [:div.container-fluid.row.mt-5
     [:div.col-8.offset-1.mb-3>h1 "Caravans"]
     [:div.col-2>button.btn.btn-success.float-right {:type "button"
                                                     :on-click #(rf/dispatch [:clear-current-caravan])
                                                     :data-toggle "modal"
                                                     :data-target "#caravanModal"}
      "Add New Caravan"]
     [caravan-table]
     [caravan-modal caravan]]))
