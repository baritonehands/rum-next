(ns baritonehands.rum-next.core
  (:require [io.pedestal.http :as server]
            [baritonehands.rum-next.service :as service]))

(defonce runnable-service (server/create-server (service/create)))

(defn dev []
  (let [server (-> (service/create {:env                    :dev
                                    ::server/join?          false
                                    ::server/secure-headers {:content-security-policy-settings {:object-src "none"}}})
                   (server/dev-interceptors)
                   (server/create-server))]
    (println "Started [DEV] server.")
    server))

(defn -main [& args]
  (println "Starter [PROD] server.")
  (server/start runnable-service))

