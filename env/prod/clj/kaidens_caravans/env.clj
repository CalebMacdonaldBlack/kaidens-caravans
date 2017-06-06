(ns kaidens-caravans.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[kaidens-caravans started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[kaidens-caravans has shut down successfully]=-"))
   :middleware identity})
