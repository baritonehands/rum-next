(ns baritonehands.rum-next.core
  (:require [rum.core :as rum]
            [baritonehands.rum-next.router :as router]
            [baritonehands.rum-next.http :as http]
            [baritonehands.rum-next.pages.index :as index]
            [baritonehands.rum-next.pages.reactive :as reactive]
            [baritonehands.rum-next.pages.local :as local]
            [baritonehands.rum-next.components.footer :as footer]
            [baritonehands.rum-next.pages.artists :as artists]
            [baritonehands.rum-next.pages.albums :as albums]
            [baritonehands.rum-next.pages.playlists :as playlists]
            [baritonehands.rum-next.utils :as utils]))

(defn page-view [component-var]
  {:name (utils/var->name component-var)
   :view @component-var})

; Define the routes here for now, until I can combine them
(def routes
  [["/"
    ["" (page-view #'index/root)]
    ["artists"
     ["" (page-view #'artists/index)]
     ["/:id" (page-view #'artists/detail)]]
    ["albums"
     ["/:id" (page-view #'albums/detail)]]
    ["playlists"
     ["" (page-view #'playlists/index)]
     ["/:id" (page-view #'playlists/detail)]]
    ["local" (page-view #'local/page)]
    ["reactive" (page-view #'reactive/page)]]])

(defonce match (atom nil))
(defonce fetching (atom nil))

(rum/defc root < rum/reactive [props]
  (cond
    (rum/react fetching) [:div
                          [:h1 "Loading..."]
                          (footer/view)]
    :else (let [route (rum/react match)
                page (some-> route :data :view)]
            (page (assoc props
                    :reitit.core/router @router/instance
                    :reitit.core/match route)))))

(defn render-root [props]
  (rum/hydrate (root props) (.getElementById js/document "app")))

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
  (let [clj-props (js->clj props :keywordize-keys true)]
    (router/init! routes on-navigate-loader)
    (render-root clj-props)))
