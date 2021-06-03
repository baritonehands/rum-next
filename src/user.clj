(ns user
  (:require [baritonehands.rum-next.core :as app]
            [io.pedestal.http :as http]
            [figwheel.main.api :as fig]))

(defn start-figwheel []
  (fig/start "dev"))

(defn stop-figwheel []
  (fig/stop-all))

(defonce server (atom nil))

(defn start-server! []
  (http/start (reset! server (app/dev))))

(defn stop-server! []
  (http/stop @server)
  (reset! server nil))
