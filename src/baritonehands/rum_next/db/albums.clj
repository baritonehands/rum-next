(ns baritonehands.rum-next.db.albums)

(defn detail [id]
  {:select [[:AlbumId :id] :Title :Album.ArtistId [:Artist.Name :ArtistName]]
   :from [:Album]
   :inner-join [:Artist [:= :Artist.ArtistId :Album.ArtistId]]
   :where [:= :AlbumId id]})

(defn for-artist [id]
  {:select [[:AlbumId :id] :Title]
   :from [:Album]
   :where [:= :ArtistId id]})
