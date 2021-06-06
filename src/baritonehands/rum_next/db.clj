(ns baritonehands.rum-next.db
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [honey.sql :as sql]
            [baritonehands.rum-next.db.config :refer [config]]))

(defonce datasource (jdbc/get-datasource config))

(defn arg->jdbc [arg]
  (cond
    (map? arg) (sql/format arg)
    (string? arg) (vector arg)
    :else arg))

(defn execute! [query]
  (jdbc/execute! datasource (arg->jdbc query) {:builder-fn rs/as-unqualified-kebab-maps}))

(defn execute-one! [query]
  (jdbc/execute-one! datasource (arg->jdbc query) {:builder-fn rs/as-unqualified-kebab-maps}))
