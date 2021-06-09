(ns baritonehands.rum-next.utils
  (:require [reitit.core :as r]
            [clojure.string :as str]))

(defn var->name [v]
  (let [{:keys [ns name]} (meta v)
        segments (drop 2 (-> ns ns-name str (str/split #"\.")))
        prefix (str/join "." segments)]
    (keyword prefix (str name))))

(defn page-url
  "Get page url from router by name.
  Ex. (page-url :pages.index/root :path-params {:id \"1\"})"
  [router page-name path-params query-params]
  (-> (r/match-by-name router page-name path-params)
      (r/match->path query-params)))
