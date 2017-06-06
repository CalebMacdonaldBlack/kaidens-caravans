(ns ^:figwheel-no-load kaidens-caravans.app
  (:require [kaidens-caravans.core :as core]
            [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
