(ns kaidens-caravans.routes.core
  (:require [kaidens-caravans.layout :as layout]
            [compojure.api.sweet :refer [defapi context GET POST PUT DELETE]]
            [ring.util.http-response :as response]))

(defn manager-page []
  (layout/render "manager.html"))

(defn home-page []
  (layout/render "home.html"))

(defapi my-routes
  (GET "/" [] (home-page))
  (context "/manager" []
    (GET "/" [] (manager-page))))
