(ns baritonehands.rum-next.router
  (:require [reitit.frontend :as rf]
            [reitit.frontend.history :as rfh]))

(defonce instance (atom nil))

(defn create! [routes]
  (reset! instance (rf/router
                     routes
                     {:data {:controllers [{:identity #(select-keys % [:path :parameters])
                                            :start    #(println "start" %&)
                                            :stop     #(println "stop" %&)}]}})))

(defn init! [routes on-navigate]
  (create! routes)
  (rfh/start! @instance on-navigate {:use-fragment false}))
