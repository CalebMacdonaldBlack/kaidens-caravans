(ns kaidens-caravans.views.header
  (:require [hiccup.core :refer :all]))

(defn view []
  [:div.jumbotron.jumbotron-fluid.p-4
   {:style "background-color: white; color: #333"}
   [:h1.ml-3.d-inline
    {:style "font-weight: 300"}
    [:span {:style "color: #F44336; font-weight: 600"} "A"]
    "ustralian "
    [:span {:style "color: #F44336; font-weight: 600"} "C"]
    "aravans "
    [:span {:style "color: #F44336; font-weight: 600"} "T"]
    "ownsville"]
   [:h2.float-right.d-inline.mr-3
    {:_:_ "_:_", :style "color: #F44336"}
    [:i.fa.fa-phone {:aria-hidden "true"}]
    " 04 2402 0205"]])
