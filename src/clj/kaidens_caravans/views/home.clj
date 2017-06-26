(ns kaidens-caravans.views.home
  (:require [hiccup.page :refer [html5 include-css]]))

(defn home-page []
  (html5 {:lang "en"}
    [:head (include-css "/css/bare.css")]
    [:body
     [:section
       [:h1 "Kaidens Caravans"]
       [:h2 "Server Side Rendering Here"]]]))
