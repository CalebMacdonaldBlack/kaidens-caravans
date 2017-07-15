(ns kaidens-caravans.components.nav
  (:require [re-frame.core :as rf]
            [accountant.core :as accountant]))

(defn nav []
  [:nav.navbar.navbar-inverse
   [:div.container-fluid
    [:div.navbar-header
     [:a.navbar-brand {:on-click #(accountant/navigate! "#/")} "Manager"]]
    [:ul.nav.navbar-nav
     [:li>a {:on-click #(accountant/navigate! "#/")} "Home"]
     [:li>a {:on-click #(accountant/navigate! "#/caravans")} "Caravans"]]]])
