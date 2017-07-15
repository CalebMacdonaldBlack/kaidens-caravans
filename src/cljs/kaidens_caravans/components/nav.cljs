(ns kaidens-caravans.components.nav
  (:require [re-frame.core :as rf]
            [accountant.core :as accountant]))

(defn nav []
  [:nav.navbar.navbar-toggleable-md.navbar-light.bg-faded
   [:button.navbar-toggler.navbar-toggler-right {:type "button"
                                                 :data-toggle "collapse"
                                                 :data-target "#navbarNavAltMarkup"
                                                 :aria-controls "navbarNavAltMarkup"
                                                 :aria-expanded "false"
                                                 :aria-label "Toggle navigation"}
    [:span.navbar-toggler-icon]]
   [:a.navbar-brand {:on-click #(accountant/navigate! "#/")} "Manager"]
   [:div#navbarNavAltMarkup.collapse.navbar-collapse
    [:ul.navbar-nav
     [:li.nav-item
      [:a.nav-link {:on-click #(accountant/navigate! "#/")} "Home"]]
     [:li.nav-item
      [:a.nav-link {:on-click #(accountant/navigate! "#/caravans")} "Caravans"]]]]])
