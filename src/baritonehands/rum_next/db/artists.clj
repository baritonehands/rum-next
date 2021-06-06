(ns baritonehands.rum-next.db.artists)

(defn index []
  {:select [[:ArtistId :id] :Name]
   :from   [:Artist]
   :order-by [[:Name :asc]]})

(defn detail [id]
  {:select [[:ArtistId :id] :Name]
   :from [:Artist]
   :where [:= :ArtistId id]})
