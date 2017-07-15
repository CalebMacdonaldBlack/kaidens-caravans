(ns kaidens-caravans.test.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [kaidens-caravans.test.core-test]))

(doo-tests 'kaidens-caravans.test.core-test)

