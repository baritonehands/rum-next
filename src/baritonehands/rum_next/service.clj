(ns baritonehands.rum-next.service
  (:require [io.pedestal.http :as server]
            [muuntaja.core :as m]
            [reitit.pedestal :as pedestal]
            [reitit.http :as http]
            [reitit.http.interceptors.parameters :as parameters]
            [reitit.http.interceptors.muuntaja :as muuntaja]
            [reitit.http.interceptors.exception :as exception]
            [baritonehands.rum-next.pages :as pages]))

(def routes
  [["/" {:get {:handler pages/index}}]
   ["/api"
    ["/number" {:get {:handler (fn [{:keys [params]}]
                                 {:status 200
                                  :body   (select-keys params [:number])})}}]]])

(defn create []
  (-> {::server/type   :jetty
       ::server/port   3000
       ::server/join?  false
       ;; no pedestal routes
       ::server/routes []}
      (server/default-interceptors)
      ;; swap the reitit router
      (pedestal/replace-last-interceptor
        (pedestal/routing-interceptor
          (http/router
            routes
            {:data {:muuntaja m/instance
                    :interceptors [(parameters/parameters-interceptor)
                                   (muuntaja/format-negotiate-interceptor)
                                   (muuntaja/format-response-interceptor)
                                   (exception/exception-interceptor)
                                   (muuntaja/format-request-interceptor)]}})))
      (server/dev-interceptors)
      (server/create-server)))
