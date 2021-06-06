(ns baritonehands.rum-next.components.footer
  (:require [rum.core :as rum]))

(rum/defc view []
  [:div
   [:h3 "Navigate"]
   [:a {:href "/"} "Root"]
   " "
   [:a {:href "/artists"} "Artists"]
   " "
   [:a {:href "/reactive"} "Reactive"]
   " "
   [:a {:href "/local"} "Local"]])
