(ns baritonehands.rum-next.pages.artists
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(rum/defc index [artists]
  [:div
   [:h1 "Artists"]
   [:ul
    (for [{:keys [id name]} artists]
      [:li {:key id} [:a {:href (str "/artists/" id)} name]])]
   (footer/view)])

(rum/defc detail [{:keys [name albums]}]
  [:div
   [:h2 name]
   [:h3 "Albums"]
   [:ul
    (for [{album-id    :id
           album-title :title} albums]
      [:li {:key album-id}
       [:a {:href (str "/albums/" album-id)} album-title]])]])
