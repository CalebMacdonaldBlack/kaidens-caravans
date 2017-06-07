(ns kaidens-caravans.views.manager
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn manager-page []
  (html5 {:lang "en"}
         [:head
          [:title "Manager"]]
         [:body
          [:div#app
           [:section
            [:h1 "Kaiden's Caravans"]
            [:h2 "Please wait while the application loads"]]]
          (include-css "/css/bare.css")
          (include-js "/js/app.js")]))
