(ns baritonehands.rum-next.db.playlists)

(defn index []
  {:select [[:PlaylistId :id] :Name]
   :from   [:Playlist]
   :order-by [[:Name :asc]]})

(defn detail [id]
  {:select [[:PlaylistId :id] :Name]
   :from [:Playlist]
   :where [:= :PlaylistId id]})
