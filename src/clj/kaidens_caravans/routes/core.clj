(ns kaidens-caravans.routes.core
  (:require [kaidens-caravans.layout :as layout]
            [kaidens-caravans.views.home :refer [home-page]]
            [kaidens-caravans.views.manager :refer [manager-page]]
            [hiccup.core :refer [html]]
            [compojure.api.sweet :refer [defapi context GET POST PUT DELETE]]
            [ring.util.http-response :refer [content-type ok]]
            [ring.util.http-response :as response]))

(defapi my-routes
  (GET "/" [] (content-type (ok (home-page)) "text/html; charset=utf-8"))
  (context "/manager" []
    (GET "/" [] (manager-page))))
