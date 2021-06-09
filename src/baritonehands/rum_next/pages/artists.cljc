(ns baritonehands.rum-next.pages.artists
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]
            [baritonehands.rum-next.components.links :as links]))

(rum/defc index [{:keys  [artists]
                  router :reitit.core/router}]
  [:div
   [:h1 "Artists"]
   [:ul
    (for [{:keys [id name]} artists]
      [:li {:key id}
       (links/page {:router      router
                    :page        :pages.artists/detail
                    :path-params {:id id}}
                   name)])]
   (footer/view)])

(rum/defc detail [{{:keys [name albums]} :artist
                   router                :reitit.core/router}]
  [:div
   [:h2 name]
   [:h3 "Albums"]
   [:ul
    (for [{album-id    :id
           album-title :title} albums]
      [:li {:key album-id}
       (links/page {:router      router
                    :page        :pages.albums/detail
                    :path-params {:id album-id}
                    :query-params {:foo "bar"}}
                   album-title)])]
   (footer/view)])
