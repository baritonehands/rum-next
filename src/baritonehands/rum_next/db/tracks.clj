(ns baritonehands.rum-next.db.tracks)

(defn for-album [id]
  {:select   [:*]
   :from     [:Track]
   :where    [:= :AlbumId id]
   :order-by [[:TrackId :asc]]})

(defn for-playlist [id]
  {:select     [:*]
   :from       [:Track]
   :inner-join [:PlaylistTrack [:= :Track.TrackId :PlaylistTrack.TrackId]]
   :where      [:= :PlaylistTrack.PlaylistId id]})
