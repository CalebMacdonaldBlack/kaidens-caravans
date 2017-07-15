(ns kaidens-caravans.db
  (:require [reagent.core :as r]))

(def default-db
  {:page :home
   :current-caravan (r/atom {})})

