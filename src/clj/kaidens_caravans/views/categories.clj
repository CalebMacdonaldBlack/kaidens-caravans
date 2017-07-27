(ns kaidens-caravans.views.categories
  (:require [hiccup.core :refer :all]))

(defn view []
  [:div
   [:a#browse
    {:style
     "display: block; position: relative; top: -115px; visibility: hidden;"}]
   [:div.container.mb-5
    [:h1.text-center.mt-5.mb-5
     {:style "color: #333"}
     "Browse Caravans"]
    [:div.row
     [:div.col-md-4
      [:div.card.lift-card
       {:style "min-height: 330px; max-height: 330px;"}
       [:img.card-img-top.style_prevu_kit
        {:style "width: 80%; margin: auto;",
         :alt "Card image cap",
         :src "/img/caravan.png"}]
       [:div.card-block [:h4.card-title "Caravan"]]]]
     [:div.col-md-4
      [:div.card.lift-card
       {:style "min-height: 330px; max-height: 330px;"}
       [:img.card-img-top
        {:style "width: 80%; margin: auto;",
         :alt "Card image cap",
         :src "/img/poptop.png"}]
       [:div.card-block [:h4.card-title "Poptop"]]]]
     [:div.col-md-4
      [:div.card.lift-card
       {:style "min-height: 330px; max-height: 330px;"}
       [:img.card-img-top
        {:style "width: 70%; margin: auto; margin-top: 2em",
         :alt "Card image cap",
         :src "/img/camper-trailer.png"}]
       [:div.card-block [:h4.card-title "Camper Trailer"]]]]]
    [:div.row.mt-5
     [:div.col-md-4.offset-md-2
      [:div.card.lift-card
       {:style "min-height: 330px; max-height: 330px;"}
       [:img.card-img-top.style_prevu_kit
        {:style "width: 70%; margin: auto; margin-top: 4em",
         :alt "Card image cap",
         :src "/img/motorhome.png"}]
       [:div.card-block [:h4.card-title "Motorhome"]]]]
     [:div.col-md-4
      [:div.card.lift-card
       {:style "min-height: 330px; max-height: 330px;"}
       [:img.card-img-top
        {:style "width: 80%; margin: auto;",
         :alt "Card image cap",
         :src "/img/boat.png"}]
       [:div.card-block [:h4.card-title "Boat"]]]]]]])
