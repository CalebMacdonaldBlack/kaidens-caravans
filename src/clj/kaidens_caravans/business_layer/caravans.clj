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
                      :axles ""
                      :features []
                      :photos []
                      :videos []
                      :archived false})

(defn create!
  [{:keys [body-params]}]
  (response/ok {:rows-affected (create-caravan! (merge default-caravan body-params))}))

(defn retrieve
  [{:keys [query-params]}]
  (response/ok (retrieve-caravans (clojure.walk/keywordize-keys query-params))))

(defn update!
  [{:keys [body-params route-params]}]
  (response/ok {:rows-affected (update-caravan! (merge default-caravan (assoc body-params :id (:id route-params))))}))

(defn delete!
  [{:keys [route-params]}]
  (response/ok {:rows-affected (delete-caravan! route-params)}))

(defn distinct-type-list []
  (response/ok (map #(:type %)(search-type))))

(defn distinct-make-list []
  (response/ok (map #(:make %) (search-make))))

(defn distinct-model-list []
  (response/ok (map #(:model %) (search-model))))

(defn distinct-condition-list []
  (response/ok (map #(:condition %) (search-condition))))
