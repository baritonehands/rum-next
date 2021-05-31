(ns baritonehands.rum-next
  (:require [baritonehands.rum-next.service :as service]
            [io.pedestal.http :as server])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (server/start (service/create)))
