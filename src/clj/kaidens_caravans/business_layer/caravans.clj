(ns kaidens-caravans.business-layer.caravans
  (:require [kaidens-caravans.db.core :refer [create-caravan!]]))

(defn create! [caravan] (create-caravan! caravan))
