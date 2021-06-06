(ns baritonehands.rum-next.db.tracks)

(defn for-album [id]
  {:select [:*]
   :from [:Track]
   :where [:= :AlbumId id]
   :order-by [[:TrackId :asc]]})
