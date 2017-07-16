(ns kaidens-caravans.routes.core
  (:require [kaidens-caravans.layout :as layout]
            [kaidens-caravans.business-layer.caravans :as caravans]
            [hiccup.core :refer [html]]
            [compojure.api.sweet :refer [defapi context GET POST PUT DELETE]]
            [ring.util.http-response :refer [content-type ok]]
            [ring.util.http-response :as response]))

(declare ^:dynamic *app-context*)

(defapi my-routes
  (GET "/" [] (layout/render "homepage.html"))

  (context "/manager" []
    (GET "/" [] (layout/render "manager.html")))

  (context "/caravans" []
    (POST "/" {:as request} (caravans/create! request))
    (GET "/" {:as request} (caravans/retrieve request))
    (PUT "/:id" {:as request} (caravans/update! request))
    (DELETE "/:id" {:as request} (caravans/delete! request))))
