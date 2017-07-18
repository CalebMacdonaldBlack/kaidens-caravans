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
                      :vin
                      :frame
                      :terrain
                      :condition
                      :bed
                      :fridge
                      :suspension
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

(defn distinct-type-list
  [{:keys [query-params]}]
  (response/ok (map :type (search-type (clojure.walk/keywordize-keys query-params)))))

(defn distinct-make-list
  [{:keys [query-params]}]
  (response/ok (map :make (search-make (clojure.walk/keywordize-keys query-params)))))

(defn distinct-model-list
  [{:keys [query-params]}]
  (response/ok (map :model (search-model (clojure.walk/keywordize-keys query-params)))))

(defn distinct-condition-list
  [{:keys [query-params]}]
  (response/ok (map :condition (search-condition (clojure.walk/keywordize-keys query-params)))))

(defn distinct-terrain-list
  [{:keys [query-params]}]
  (response/ok (map :terrain (search-terrain (clojure.walk/keywordize-keys query-params)))))

(defn distinct-bed-list
  [{:keys [query-params]}]
  (response/ok (map :bed (search-bed (clojure.walk/keywordize-keys query-params)))))

(defn distinct-fridge-list
  [{:keys [query-params]}]
  (response/ok (map :fridge (search-fridge (clojure.walk/keywordize-keys query-params)))))

(defn distinct-frame-list
  [{:keys [query-params]}]
  (response/ok (map :frame (search-frame (clojure.walk/keywordize-keys query-params)))))

(defn distinct-suspension-list
  [{:keys [query-params]}]
  (response/ok (map :suspension (search-suspension (clojure.walk/keywordize-keys query-params)))))
