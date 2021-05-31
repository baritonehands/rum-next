(defproject baritonehands/rum-next "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.5.9"]
                 [io.pedestal/pedestal.jetty "0.5.9"]
                 [metosin/jsonista "0.3.3"]
                 [metosin/muuntaja "0.6.8"]
                 [metosin/reitit-core "0.5.13"]
                 [metosin/reitit-pedestal "0.5.13"]
                 [metosin/reitit-interceptors "0.5.13"]
                 [rum "0.12.6"]]
  :main ^:skip-aot baritonehands.rum-next
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
