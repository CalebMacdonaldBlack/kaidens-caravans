(ns kaidens-caravans.views.jumbotron)

(defn view []
 [:div.jumbotron
  {:style
   "background: url(/img/banner.jpg) no-repeat center center fixed; background-size: cover; background-position-y: -32em; height: 30em;"}
  [:p.lead.text-center
   {:style "margin-top: 180px"}
   [:a.btn.btn-danger.btn-md
    {:role "button", :href "#browse"}
    "Browse Caravans"]]])

