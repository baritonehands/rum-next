(ns baritonehands.rum-next.page-data.playlists
  (:require [baritonehands.rum-next.db :as db]
            [baritonehands.rum-next.db.playlists :as playlists-db]
            [baritonehands.rum-next.db.tracks :as tracks-db]))

(defn index! [_]
  {:playlists (-> (playlists-db/index)
                  (db/execute!))})

(defn detail! [request]
  (let [id (get-in request [:path-params :id])
        playlist (-> (playlists-db/detail id)
                     (db/execute-one!))
        tracks (-> (tracks-db/for-playlist id)
                   (db/execute!))]
    {:playlist (assoc playlist :tracks tracks)}))
