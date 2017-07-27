(ns kaidens-caravans.views.nav
  (:require [hiccup.core :refer :all]))

(defn view []
  [:nav.navbar.navbar-inverse.navbar-toggleable-md
    {:style "background-color: #333"}
    [:button.navbar-toggler.navbar-toggler-right
     {:aria-label "Toggle navigation",
      :aria-expanded "false",
      :aria-controls "navbarSupportedContent",
      :data-target "#navbarSupportedContent",
      :data-toggle "collapse",
      :type "button"}
     [:span.navbar-toggler-icon]]
    [:div#navbarSupportedContent.collapse.navbar-collapse
     [:h2.float-right.d-inline.mb-0.ml-4.nav-phone.hidden-xs-up
      {:_:_ "_:_", :style "color: #333"}
      [:i.fa.fa-phone {:aria-hidden "true"}]
      " 04 2402 0205"]
     [:ul.navbar-nav.mx-auto
      [:li.nav-item.active
       [:a.nav-link {:href "#"} "Home " [:span.sr-only]]]
      [:li.nav-item [:a.nav-link {:href "#"} "Caravans "]]
      [:li.nav-item [:a.nav-link {:href "#"} "Pop Tops "]]
      [:li.nav-item [:a.nav-link {:href "#"} "Camper Trailers "]]
      [:li.nav-item [:a.nav-link {:href "#"} "Motor Homes "]]
      [:li.nav-item [:a.nav-link {:href "#"} "Boats "]]
      [:li.nav-item [:a.nav-link {:href "#"}]]]
     [:h2.float-right.d-inline.mb-0.mr-4.nav-phone.hidden-xs-up
      {:_:_ "_:_", :style "color: #F44336"}
      [:i.fa.fa-phone {:aria-hidden "true"}]
      " 04 2402 0205"]]])
