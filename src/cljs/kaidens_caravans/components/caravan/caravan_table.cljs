(ns kaidens-caravans.components.caravan.caravan-table
  (:require [re-frame.core :as rf]))

(defn- caravan-table-row [{:keys [vin make model type terrain feet tonne year price archived] :as caravan}]
  [:tr {:data-toggle "modal"
        :data-target "#caravanModal"
        :on-click (fn [] (reset! @(rf/subscribe [:current-caravan]) caravan))}
   [:td (if archived
            [:span.badge.badge-warning "Disabled"]
            [:span.badge.badge-success "Enabled"])]
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
     [:table.table.table-striped.table-hover
      [:thead
       [:tr
        [:th "Status"]
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
         ^{:key (:id caravan)}
         [caravan-table-row caravan])]]]))
