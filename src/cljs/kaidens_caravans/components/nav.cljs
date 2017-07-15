(ns kaidens-caravans.components.nav
  (:require [re-frame.core :as rf]
            [accountant.core :as accountant]))

(defn nav []
  [:nav>label
   [:input {:type "checkbox"}]
   [:header>a "Kaidens " [:strong "Caravans"]]
   [:ul
    [:li>a {:on-click #(accountant/navigate! "#/")} "Home"]
    [:li>a {:on-click #(accountant/navigate! "#/caravans")} "Caravans"]]])
