(ns kaidens-caravans.views.stock-listing
  (:require [clojure.string :as s]
            [clojure.contrib.humanize :refer [intcomma]]))

(defn view [listings type]
  [:div.container
   [:div.row.mt-3.mb-3 [:h1.mx-auto (s/capitalize type)]]
   [:div.row
    (for [listing listings]
      [:div.col-md-6.mb-3
       [:div.card
        [:div.carousel.slide
         {:data-interval "false" :data-ride "carousel" :id (:id listing)}
         [:div.carousel-inner
          {:role "listbox"}
          [:div.carousel-item.active
           [:img.d-block.img-fluid
            {:alt "First slide"
             :src
                  "http://res.cloudinary.com/lyzppej5b/image/upload/c_fill,h_600,w_1000/v1500714560/20170419_104121-1500x843_egohit.jpg"}]
           [:div.carousel-caption.d-none.d-md-block
            [:h3 (str (:year listing) " " (:make listing) " " (:model listing) " " (:feet listing) "ft")]]]
          [:div.carousel-item
           [:img.d-block.img-fluid
            {:alt "First slide"
             :src
                  "http://res.cloudinary.com/lyzppej5b/image/upload/c_fill,h_600,w_1000/v1500714560/20170419_104136-1500x843_j5c845.jpg"}]
           [:div.carousel-caption.d-none.d-md-block
            [:p
             "2 x Wall Mounted TV's "
             [:br]
             "\n                                    2 Door 186Litre Fridge/Freezer 3 Way "
             [:br]
             "\n                                    Microwave "
             [:br]
             "\n                                    Satellite Dish & Controller"]]]]
         [:a.carousel-control-prev
          {:data-slide "prev" :role "button" :href (str "#" (:id listing))}
          [:span.carousel-control-prev-icon {:aria-hidden "true"}]
          [:span.sr-only "Previous"]]
         [:a.carousel-control-next
          {:data-slide "next" :role "button" :href (str "#" (:id listing))}
          [:span.carousel-control-next-icon {:aria-hidden "true"}]
          [:span.sr-only "Next"]]]
        [:div.card-block
         [:h3.d-inline (str "$" (intcomma (int (:price listing))))]
         [:div.float-right.d-inline
          [:a.btn.btn-danger {:style "color: white !important"} "Enquire"]
          [:a.btn.btn-primary
           {:href (str "/" (:id listing) ".html") :style "color: white !important"}
           "More"]]]]])]])

