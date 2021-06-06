(ns baritonehands.rum-next.page-data.artists
  (:require [baritonehands.rum-next.db :as db]
            [baritonehands.rum-next.db.artists :as artists-db]
            [baritonehands.rum-next.db.albums :as albums-db]))

(defn index! [_]
  (-> (artists-db/index)
      (db/execute!)))

(defn detail! [request]
  (let [id (get-in request [:path-params :id])
        artist (-> (artists-db/detail id)
                   (db/execute-one!))
        albums (-> (albums-db/for-artist id)
                   (db/execute!))]
    (assoc artist :albums albums)))
