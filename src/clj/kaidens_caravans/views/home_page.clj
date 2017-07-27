(ns kaidens-caravans.views.home-page
  (:require [hiccup.core :refer :all]
            [kaidens-caravans.views.categories :as categories]
            [kaidens-caravans.views.footer :as footer]
            [kaidens-caravans.views.head-body :as head-body]
            [kaidens-caravans.views.header :as header]
            [kaidens-caravans.views.jumbotron :as jumbotron]
            [kaidens-caravans.views.nav :as nav]))

(defn view []
  (head-body/view
    (header/view)
    (nav/view)
    (jumbotron/view)
    (categories/view)
    (footer/view)))
