(ns kaidens-caravans.components.new-caravan.page
  (:require [re-frame.core :as rf]
    [reagent.core :as r]
    [secretary.core :as secretary]))

(secretary/defroute "/new-caravan" []
                    (rf/dispatch [:set-active-page :new-caravan]))

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

(defn- image-text-form-input [key ratom index]
  [:div.col-9>input.form-control {:type      "text"
                                  :on-change #(swap! ratom assoc-in [key index :description (.-target.value %)])}])
;:value     (get-in @ratom keys)}])

(defn- image-input [key ratom index]
  [:div.col-9>input.form-control {:type "file"
                                  :on-change (fn [e]
                                               (let [file (first (array-seq (.. e -target -files)))
                                                     file-reader (js/FileReader.)]
                                                 (set! (.-onload file-reader)
                                                       (fn [e]
                                                         (swap! ratom assoc-in [key index :base64] (-> e .-target .-result))
                                                         (swap! ratom assoc-in [key index :filename] (.-name file))))
                                                 (.readAsDataURL file-reader file)))}])

(defn- image-uploads [ratom key images]
  [:div
   (for [image images]
     (let [index (.indexOf (to-array images) image)]
       ^{:key (str "image-" index)}
       [:div
        [:h4 "Images"]
        [:div.row.form-group
         [:div.col-3.col-form-label "Image File"]
         [image-input key ratom index]
         [:div.col-3.col-form-label "Description"]
         [image-text-form-input key ratom index]]]))
   [:button.btn.btn-success {:on-click #(swap! ratom update key conj {})} "Add Image"]])

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
   [form-input "price" :price "number" caravan]
   [:hr]
   [image-uploads caravan :photos (:photos @caravan)]])
(defn view []
  [:div.container-fluid.row.mt-5
   [:div.col-8.offset-1.mb-3>h1 "New Caravan"]
   [:div.col-8.offset-1
    [modify-caravan-form @(rf/subscribe [:current-caravan])]]])
