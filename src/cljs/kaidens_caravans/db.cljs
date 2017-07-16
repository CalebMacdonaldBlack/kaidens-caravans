(ns kaidens-caravans.db
  (:require [reagent.core :as r]))

(def default-db
  {:page :home
   :hide-disabled true
   :current-caravan (r/atom {})})

