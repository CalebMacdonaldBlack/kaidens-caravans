(ns kaidens-caravans.test.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [kaidens-caravans.test.core-test]
            [kaidens-caravans.test.subscriptions]
            [kaidens-caravans.test.handler]))

(doo-tests 'kaidens-caravans.test.core-test
           'kaidens-caravans.test.subscriptions
           'kaidens-caravans.test.handler)
