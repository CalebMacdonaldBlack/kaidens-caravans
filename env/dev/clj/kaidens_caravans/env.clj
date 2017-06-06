(ns kaidens-caravans.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [kaidens-caravans.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[kaidens-caravans started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[kaidens-caravans has shut down successfully]=-"))
   :middleware wrap-dev})
