(ns user
  (:require [mount.core :as mount]
            [kaidens-caravans.figwheel :refer [start-fw stop-fw cljs]]
            kaidens-caravans.core))

(defn start []
  (mount/start-without #'kaidens-caravans.core/repl-server))

(defn stop []
  (mount/stop-except #'kaidens-caravans.core/repl-server))

(defn restart []
  (stop)
  (start))


