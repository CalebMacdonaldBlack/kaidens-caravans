(ns kaidens-caravans.business-layer.caravans
  (:require [kaidens-caravans.db.core :refer :all]
            [ring.util.http-response :as response]))

(defn create!
  [{:keys [body-params]}]
  (response/ok {:rows-affected (create-caravan! body-params)}))

(defn retrieve []
  (response/ok (retrieve-caravans)))

(defn update!
  [{:keys [body-params route-params]}]
  (response/ok {:rows-affected (update-caravan! (assoc body-params :id (:id route-params)))}))

(defn delete!
  [{:keys [route-params]}]
  (response/ok {:rows-affected (delete-caravan! route-params)}))
