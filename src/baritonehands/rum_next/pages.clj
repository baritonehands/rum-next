(ns baritonehands.rum-next.pages
  (:require [rum.core :as rum]
            [baritonehands.rum-next.pages.index :as index]))

(defn full-page [component]
  [:html
   [:head
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]]
   [:body
    {:dangerouslySetInnerHTML {:__html (rum/render-html (component))}}]])

(defmacro defpage [name component]
  `(defn ~name [~'_]
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (rum/render-static-markup (full-page ~component))}))

(defpage index index/root)
