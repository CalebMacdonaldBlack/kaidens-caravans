(ns kaidens-caravans.business-layer.caravans
  (:require [kaidens-caravans.db.core :refer :all]
            [ring.util.http-response :as response]))

(defn create!
  [{:keys [body-params]}]
  (response/ok {:rows-affected (create-caravan! body-params)}))

(defn retrieve []
  (response/ok (retrieve-caravans)))
