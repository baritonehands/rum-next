(ns baritonehands.rum-next.pages.playlists
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]
            [baritonehands.rum-next.components.links :as links]))

(rum/defc index [{:keys  [playlists]
                  router :reitit.core/router}]
  [:div
   [:h1 "Playlist"]
   [:ul
    (for [{:keys [id name]} playlists]
      [:li {:key id}
       (links/page {:router      router
                    :page        :pages.playlists/detail
                    :path-params {:id id}}
                   name)])]
   (footer/view)])

(rum/defc detail [{{:keys [name tracks]} :playlist
                   router                :reitit.core/router}]
  [:div
   [:h2 name]
   [:h3 "Tracks"]
   [:ul
    (for [{track-id   :track-id
           track-name :name
           album-id   :album-id} tracks]
      [:li {:key track-id}
       (links/page {:router      router
                    :page        :pages.albums/detail
                    :path-params {:id album-id}}
                   track-name)])]
   (footer/view)])
