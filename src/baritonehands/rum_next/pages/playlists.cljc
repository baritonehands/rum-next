(ns baritonehands.rum-next.pages.playlists
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(rum/defc index [playlists]
  [:div
   [:h1 "Playlist"]
   [:ul
    (for [{:keys [id name]} playlists]
      [:li {:key id} [:a {:href (str "/playlists/" id)} name]])]
   (footer/view)])

(rum/defc detail [{:keys [name tracks]}]
  [:div
   [:h2 name]
   [:h3 "Tracks"]
   [:ul
    (for [{track-id   :track-id
           track-name :name
           album-id   :album-id} tracks]
      [:li {:key track-id}
       [:a {:href (str "/albums/" album-id)} track-name]])]
   (footer/view)])
