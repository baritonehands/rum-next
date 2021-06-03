(ns baritonehands.rum-next.pages.server
  (:require [rum.core :as rum]))

(defn full-page [component]
  [:html
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:link {:rel "stylesheet" :href "css/reset.css"}]]
   [:body
    [:div#app {:dangerouslySetInnerHTML {:__html (rum/render-html (component))}}]
    [:script {:src "js/app.js" :type "text/javascript"}]
    [:script {:dangerouslySetInnerHTML {:__html "baritonehands.rum_next.core.init();"}}]]])

(defmacro defpage [name component]
  `(defn ~name [~'_]
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (rum/render-static-markup (full-page ~component))}))
