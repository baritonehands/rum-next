(ns baritonehands.rum-next.pages
  (:require
    [baritonehands.rum-next.pages.index :as index]
    [baritonehands.rum-next.pages.artists :as artists]
    [baritonehands.rum-next.page-data.artists :as artists-data]
    [baritonehands.rum-next.pages.albums :as albums]
    [baritonehands.rum-next.page-data.albums :as albums-data]
    [baritonehands.rum-next.pages.playlists :as playlists]
    [baritonehands.rum-next.page-data.playlists :as playlists-data]
    [baritonehands.rum-next.pages.reactive :as reactive]
    [baritonehands.rum-next.pages.local :as local]
    [baritonehands.rum-next.pages.server :refer [page-handler]]))

(def routes
  [["/"
    ["" (page-handler index/root (constantly {}))]
    ["artists"
     ["" (page-handler artists/index artists-data/index!)]
     ["/:id" (page-handler artists/detail artists-data/detail!)]]
    ["albums"
     ["/:id" (page-handler albums/detail albums-data/detail!)]]
    ["playlists"
     ["" (page-handler playlists/index playlists-data/index!)]
     ["/:id" (page-handler playlists/detail playlists-data/detail!)]]
    ["local" (page-handler local/page (constantly {:name     "Brian Gregg"
                                                   :address1 "123 Main St."}))]
    ["reactive" (page-handler reactive/page (constantly {:counter 10}))]]])
