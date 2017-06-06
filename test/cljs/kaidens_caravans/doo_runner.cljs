(ns kaidens-caravans.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [kaidens-caravans.core-test]))

(doo-tests 'kaidens-caravans.core-test)

