(ns kaidens-caravans.components.caravan.caravans-page
  (:require [re-frame.core :as rf]
            [reagent.core :as r]
            [kaidens-caravans.components.caravan.caravan-table :refer [caravan-table]]
            [kaidens-caravans.components.caravan.caravan-modal :refer [caravan-modal]]
            [ajax.core :refer [GET POST]]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:load-caravans])
                    (rf/dispatch [:set-active-page :caravans]))

(defn search-dropdown [id key caravan list endpoint]
   [:div.dropdown {:id id}
    [:input.form-control.dropdown-toggle {:id (str "input-" id)
                                          :on-change #(do (rf/dispatch [:search-field-updated (.-target.value %) endpoint list id])
                                                          (swap! caravan assoc key (.-target.value %)))
                                          :value (key @caravan)
                                          :data-toggle "dropdown"
                                          :aria-haspopup "true"
                                          :aria-expanded "false"}]
    [:div.dropdown-menu.c-search-dropdown {:aria-labelledby (str "input-" id)}
     (for [item @list]
       ^{:key (str endpoint item)}
       [:a.dropdown-item {:on-click #(swap! caravan assoc key item)} item])]])

(defn caravans-page []
  (let [caravan @(rf/subscribe [:current-caravan])
        new-caravan-toggle (r/atom false)
        list (r/atom [])]
    [:div.container-fluid.row.mt-5
     [:div.col-8.offset-1.mb-3>h1 "Caravans"]
     [:div.form-group
      [search-dropdown "make-search" :make caravan list "/caravans/makes"]]
     [:div.col-2>button.btn.btn-success.float-right {:type "button"
                                                     :on-click #(rf/dispatch [:clear-current-caravan])
                                                     :data-toggle "modal"
                                                     :data-target "#caravanModal"}
      "Add New Caravan"]
     [:div.col-2.offset-1.mb-4
      [:label.form-check-label
       [:input.form-check-input {:type "checkbox"
                                 :on-change #(rf/dispatch [:set-hide-disabled (.-target.checked %)])
                                 :checked @(rf/subscribe [:hide-disabled])}]
       " Hide disabled"]]
     [caravan-table]
     [caravan-modal caravan]]))
