(ns kaidens-caravans.views.head-body
  (:require [hiccup.core :refer :all]))

(defn view [& params]
  [:html
    [:head
     [:meta
      {:content "text/html; charset=UTF-8", :http-equiv "Content-Type"}]
     [:meta
      {:content "width=device-width, initial-scale=1", :name "viewport"}]
     [:title "ACT"]]
    [:body
     (for [p params] p)
     [:script
      {:src
       "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"}]
     [:link
      {:href
            "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css",
       :rel "stylesheet"}]
     [:script
      {:src
       "https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"}]
     [:script
      "\n    $(document).ready(function () {\n\n        var menu = $('nav');\n        var origOffsetY = menu.offset().top;\n\n        function scroll() {\n            if ($(window).scrollTop() >= origOffsetY) {\n                $('nav').addClass('fixed-top');\n                $('.nav-phone').removeClass('hidden-xs-up');\n            } else {\n                $('nav').removeClass('fixed-top');\n                $('.nav-phone').addClass('hidden-xs-up');\n            }\n        }\n        document.onscroll = scroll;\n    });\n"]
     [:script {:src "/js/material.js"}]
     [:script {:src "/js/website_custom.js"}]
     [:link {:href "/css/material.css", :rel "stylesheet"}]
     [:link {:href "/css/website_custom.css", :rel "stylesheet"}]]])
