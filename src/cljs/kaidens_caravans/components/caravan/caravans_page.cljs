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