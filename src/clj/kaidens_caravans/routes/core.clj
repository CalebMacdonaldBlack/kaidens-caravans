(ns kaidens-caravans.routes.core
  (:require [kaidens-caravans.layout :as layout]
            [kaidens-caravans.business-layer.caravans :as caravans]
            [kaidens-caravans.business-layer.cms :as cms]
            [kaidens-caravans.views.home-page :as home-page]
            [hiccup.core :refer [html]]
            [compojure.api.sweet :refer [defapi context GET POST PUT DELETE]]
            [ring.util.http-response :refer [content-type ok]]
            [ring.util.http-response :as response]))

(declare ^:dynamic *app-context*)

(defapi my-routes
  (GET "/" [] (html (home-page/view)))
  (GET "/caravan" [] (layout/render "caravan.html"))
  (GET "/single-caravan" [] (layout/render "single_caravan.html"))

  (context "/manager" []
    (GET "/" [] (layout/render "manager.html")))

  (POST "/cms" [] (cms/upload!))

  (context "/caravans" []
    (POST "/" {:as request} (caravans/create! request))
    (GET "/" {:as request} (caravans/retrieve request))
    (PUT "/:id" {:as request} (caravans/update! request))
    (DELETE "/:id" {:as request} (caravans/delete! request))

    (GET "/type" {:as request} (caravans/distinct-type-list request))
    (GET "/make" {:as request} (caravans/distinct-make-list request))
    (GET "/model" {:as request} (caravans/distinct-model-list request))
    (GET "/condition" {:as request} (caravans/distinct-condition-list request))
    (GET "/terrain" {:as request} (caravans/distinct-terrain-list request))
    (GET "/bed" {:as request} (caravans/distinct-bed-list request))
    (GET "/fridge" {:as request} (caravans/distinct-fridge-list request))
    (GET "/frame" {:as request} (caravans/distinct-frame-list request))
    (GET "/suspension" {:as request} (caravans/distinct-suspension-list request))))
