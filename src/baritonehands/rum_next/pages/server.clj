(ns baritonehands.rum-next.pages.server
  (:require [rum.core :as rum]
            [muuntaja.core :as m]))

(defn full-page [component props]
  [:html
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:link {:rel "stylesheet" :href "css/reset.css"}]]
   [:body
    [:div#app {:dangerouslySetInnerHTML
               {:__html (rum/render-html (component props))}}]
    [:script {:src "js/app.js" :type "text/javascript"}]
    [:script {:dangerouslySetInnerHTML
              {:__html (str "baritonehands.rum_next.core.init(" (slurp (m/encode "application/json" props)) ");")}}]]])

(defn page-handler [component props-fn]
  (fn [{headers                 :headers
        {:keys [field subtype]} :accept}]
    (let [debug-delay (-> (get headers "x-debug-delay" "0")
                          (Integer/parseInt))
          props (props-fn)]
      (if (= subtype "html")
        {:status  200
         :headers {"Content-Type" field}
         :body    (rum/render-static-markup (full-page component props))}
        (do
          (when (pos? debug-delay)
            (Thread/sleep debug-delay))
          {:status 200
           :body   props})))))

(defmacro defpage [name component]
  `(defn ~name [~'req]
     (println (get ~'req :accept))
     {:status  200
      :headers {"Content-Type" "text/html"}
      :body    (rum/render-static-markup (full-page ~component (constantly {})))}))
