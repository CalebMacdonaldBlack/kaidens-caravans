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
          (include-css "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css")
          (include-js "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js")
          (include-js "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js")
          (include-js "/js/app.js")]))
