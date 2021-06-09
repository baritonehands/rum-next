(ns baritonehands.rum-next.page-data.albums
  (:require [baritonehands.rum-next.db :as db]
            [baritonehands.rum-next.db.albums :as albums-db]
            [baritonehands.rum-next.db.tracks :as tracks-db]))

(defn detail! [request]
  (let [id (get-in request [:path-params :id])
        album (-> (albums-db/detail id)
                  (db/execute-one!))
        tracks (-> (tracks-db/for-album id)
                   (db/execute!))]
    {:album (assoc album :tracks tracks)}))
