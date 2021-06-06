(ns baritonehands.rum-next.service
  (:require [io.pedestal.http :as server]
            [io.pedestal.http.content-negotiation :as content]
            [muuntaja.core :as m]
            [reitit.pedestal :as pedestal]
            [reitit.http :as http]
            [reitit.http.interceptors.parameters :as parameters]
            [reitit.http.interceptors.muuntaja :as muuntaja]
            [reitit.http.interceptors.exception :as exception]
            [baritonehands.rum-next.pages :as pages]))

(def routes
  (into
    pages/routes
    [["/api"
      ["/number" {:get {:handler (fn [{:keys [params]}]
                                   {:status 200
                                    :body   (select-keys params [:number])})}}]]]))

(defn create
  ([] (create {}))
  ([opts]
   (-> {:env                   :prod
        ::server/type          :jetty
        ::server/port          3000
        ::server/resource-path "/public"
        ;; no pedestal routes
        ::server/routes        []}
       (merge opts)
       (server/default-interceptors)
       ;; swap the reitit router
       (pedestal/replace-last-interceptor
         (pedestal/routing-interceptor
           (http/router
             routes
             {:data {:muuntaja     m/instance
                     :interceptors [(parameters/parameters-interceptor)
                                    (muuntaja/format-negotiate-interceptor)
                                    (muuntaja/format-response-interceptor)
                                    (exception/exception-interceptor)
                                    (muuntaja/format-request-interceptor)]}})))
       (update ::server/interceptors conj (content/negotiate-content ["text/html" "application/json"])))))
