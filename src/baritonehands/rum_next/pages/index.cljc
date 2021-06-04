(ns baritonehands.rum-next.pages.index
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(rum/defc root [props]
  [:div
   [:h1 "Hello, " (get props :foo "World")]
   (footer/view)])

