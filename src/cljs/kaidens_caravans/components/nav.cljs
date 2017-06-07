(ns kaidens-caravans.components.nav)

(defn nav []
  [:nav>label
   [:input {:type "checkbox"}]
   [:header>a "Kaidens" [:strong "Caravans"]]
   [:ul
    [:li>a "Caravans"]]])
