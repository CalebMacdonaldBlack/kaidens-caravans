(ns kaidens-caravans.components.home.page
  (:require [re-frame.core :as rf]
            [secretary.core :as secretary]))

(secretary/defroute "/" []
                    (rf/dispatch [:set-active-page :home]))

(defn view []
  [:div.container>h1 "Home Page"])
