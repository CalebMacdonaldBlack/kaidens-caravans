(ns kaidens-caravans.components.caravan.caravan-table
  (:require [re-frame.core :as rf]))

(defn- caravan-table-row [{:keys [vin make model type terrain feet tonne year price] :as caravan}]
  [:tr {:data-toggle "modal"
        :data-target "#caravanModal"
        :on-click (fn [] (reset! @(rf/subscribe [:current-caravan]) caravan))}
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
