(ns kaidens-caravans.components.caravan.caravan-modal
  (:require [re-frame.core :as rf]
            [reagent.core :as r]))

(defn search-dropdown [id key caravan list endpoint]
  [:div.dropdown {:id id}
   [:input.form-control.dropdown-toggle {:id (str "input-" id)
                                         :on-change #(do (rf/dispatch [:search-field-updated (str endpoint "?query=" (.-target.value %)) list id])
                                                         (swap! caravan assoc key (.-target.value %)))
                                         :value (key @caravan)
                                         :on-click #(rf/dispatch [:search-field-updated (str endpoint "?query=" (.-target.value %)) list id])
                                         :data-toggle "dropdown"
                                         :aria-haspopup "true"
                                         :aria-expanded "false"}]
   [:i.fa.fa-angle-down.fa-lg.c-input-right {:aria-hidden "true"}]
   [:div.dropdown-menu.c-search-dropdown {:aria-labelledby (str "input-" id)}
    (for [item (sort-by str @list)]
      ^{:key (str endpoint item)}
      [:a.dropdown-item {:on-click #(swap! caravan assoc key item)} item])]])

(defn form-search-dropdown [name id key caravan list endpoint]
  [:div.form-group.row
   [:label.col-3.col-form-label name]
   [:div.col-9
    [search-dropdown id key caravan list endpoint]]])

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

(defonce make-list (r/atom []))
(defonce model-list (r/atom []))
(defonce condition-list (r/atom []))
(defonce type-list (r/atom []))
(defonce frame-list (r/atom []))
(defonce terrain-list (r/atom []))
(defonce bed-list (r/atom []))
(defonce fridge-list (r/atom []))
(defonce suspension-list (r/atom []))

(defn- modify-caravan-form [caravan]
  [:div
   (when (:archived @caravan)
     [:div.alert.alert-warning {:role "alert"}
      [:i.fa.fa-warning]
      " This caravan is disabled and will not show up on the website."])
   [form-select "Status" :archived [["Enabled" false] ["Disabled" true]] caravan #(= "true" %)]
   [form-search-dropdown "Make" "search-make" :make caravan make-list "/caravans/make"]
   [form-search-dropdown "Model" "search-model" :model caravan model-list "/caravans/model"]
   [form-search-dropdown "Type" "search-type" :type caravan type-list "/caravans/type"]
   [form-input "VIN Number" :vin "text" caravan]
   [form-search-dropdown "Frame" "search-frame" :frame caravan frame-list "/caravans/frame"]
   [form-search-dropdown "Terrain" "search-terrain" :terrain caravan terrain-list "/caravans/terrain"]
   [form-input "Length (feet)" :feet "number" caravan]
   [form-input "Weight (Tonne)" :tonne "number" caravan]
   [form-input "Year" :year "number" caravan]
   [form-input "Axles" :axles "text" caravan]
   [form-search-dropdown "Condition" "search-condition" :condition caravan condition-list "/caravans/condition"]
   [form-search-dropdown "Bed" "search-Bed" :bed caravan bed-list "/caravans/bed"]
   [form-search-dropdown "Fridge" "search-fridge" :fridge caravan fridge-list "/caravans/fridge"]
   [form-search-dropdown "Suspension" "search-suspension" :suspension caravan suspension-list "/caravans/suspension"]
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

