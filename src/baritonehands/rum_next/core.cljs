(ns baritonehands.rum-next.core
  (:require [rum.core :as rum]
            [reitit.frontend :as rf]
            [reitit.frontend.history :as rfh]
            [reitit.frontend.controllers :as rfc]
            [baritonehands.rum-next.http :as http]
            [baritonehands.rum-next.pages.index :as index]
            [baritonehands.rum-next.pages.reactive :as reactive]
            [baritonehands.rum-next.pages.local :as local]
            [baritonehands.rum-next.components.footer :as footer]
            [baritonehands.rum-next.pages.artists :as artists]
            [baritonehands.rum-next.pages.albums :as albums]))

; Define the routes here for now, until I can combine them
(def routes
  [["/"
    ["" {:view index/root}]
    ["artists"
     ["" {:view artists/index}]
     ["/:id" {:view artists/detail}]]
    ["albums"
     ["/:id" {:view albums/detail}]]
    ["local" {:view local/page}]
    ["reactive" {:view reactive/page}]]])

(defonce match (atom nil))
(defonce fetching (atom nil))

(defn create-router []
  (rf/router
    routes
    {:data {:controllers [{:identity #(select-keys % [:path :parameters])
                           :start    #(println "start" %&)
                           :stop     #(println "stop" %&)}]}}))

(rum/defc root < rum/reactive [props]
  (cond
    (rum/react fetching) [:div
                          [:h1 "Loading..."]
                          (footer/view)]
    :else (let [page (some-> (rum/react match) :data :view)]
            (page props))))

(defn render-root [props]
  (rum/hydrate (root props) (.getElementById js/document "app")))

(defn on-navigate [new-match]
  (swap! match (fn [old-match]
                 (if new-match
                   (assoc new-match :controllers (rfc/apply-controllers (:controllers old-match) new-match))))))

(defn on-navigate-loader [new-match]
  (if @match
    (swap! fetching (fn [old-fetch]
                      (when old-fetch
                        (http/abort old-fetch))
                      (let [resp (http/fetch (:path new-match))]
                        (-> resp
                            (http/json->clj)
                            (.then (fn [new-props]
                                     (render-root new-props)
                                     (reset! match new-match)
                                     (reset! fetching nil))))
                        resp)))
    (reset! match new-match)))

(defn init [props]
  (let [r (create-router)
        clj-props (js->clj props :keywordize-keys true)]
    (rfh/start! r on-navigate-loader {:use-fragment false})
    (render-root clj-props)))
