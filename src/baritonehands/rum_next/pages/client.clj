(ns baritonehands.rum-next.pages.client)

(defmacro defpage [name component]
  `(defn ~name [~'_]
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (rum/render-static-markup (full-page ~component))}))
