(ns kaidens-caravans.app
  (:require [kaidens-caravans.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
