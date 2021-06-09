(ns baritonehands.rum-next.components.links
  (:require [baritonehands.rum-next.utils :as utils]))

(defn page [{:keys [router page path-params query-params]} & children]
  (let [url (utils/page-url router page path-params query-params)]
    (into [:a {:href url}] children)))
