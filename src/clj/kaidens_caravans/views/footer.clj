(ns kaidens-caravans.views.footer
  (:require [hiccup.core :refer :all]))

(defn view []
 [:div.container-fluid
  {:style "background-color: #333; color: white"}
  [:div.row
   {:style "margin: 0; margin-left: 2em"}
   [:div.col-md-4
    [:iframe
     {:style "border:none; margin-bottom: -.4em;",
      :height "400px",
      :width "100%",
      :src "https://snazzymaps.com/embed/4061"}]]
   [:div.col-md-7.ml-5
    [:h2.mt-5
     {:style "font-weight: 100;"}
     [:i.fa.fa-map-marker {:aria-hidden "true"}]
     " 464 Ingham Rd Townsville 4814"]
    [:h2
     {:_:_ "_:_", :style "color: #F44336"}
     [:i.fa.fa-phone {:aria-hidden "true"}]
     " 04 2402 0205"]
    [:h2.mb-5
     {:style "font-weight: 100;"}
     [:i.fa.fa-envelope-o {:aria-hidden "true"}]
     " enquiries@australiancaravanstownsville.com"]
    [:p.mt-5
     {:style "color: darkgray"}
     "Australian Caravans Townsville © 2017"]]]])
