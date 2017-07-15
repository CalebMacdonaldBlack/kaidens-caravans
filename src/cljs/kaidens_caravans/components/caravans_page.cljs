(ns kaidens-caravans.components.caravans-page
  (:require [re-frame.core :as rf]
            [secretary.core :as secretary]))

(secretary/defroute "/caravans" []
                    (rf/dispatch [:set-active-page :caravans]))

(defn caravans-page []
  [:section>h1 "Caravans Page"])
