(ns kaidens-caravans.business-layer.caravans
  (:require [kaidens-caravans.db.core :refer :all]
            [ring.util.http-response :as response]))

(def default-caravan {:type ""
                      :make ""
                      :model ""
                      :price 0
                      :year 0
                      :feet 0
                      :tonne 0
                      :features []
                      :photos []
                      :videos []})

(defn create!
  [{:keys [body-params]}]
  (response/ok {:rows-affected (create-caravan! (merge default-caravan body-params))}))

(defn retrieve []
  (response/ok (retrieve-caravans)))

(defn update!
  [{:keys [body-params route-params]}]
  (response/ok {:rows-affected (update-caravan! (assoc body-params :id (:id route-params)))}))

(defn delete!
  [{:keys [route-params]}]
  (response/ok {:rows-affected (delete-caravan! route-params)}))
