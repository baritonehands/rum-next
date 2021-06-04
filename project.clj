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
                 [metosin/reitit-frontend "0.5.13"]
                 [rum "0.12.6"]
                 [org.clojure/clojurescript "1.10.764"]]
  :main ^:skip-aot baritonehands.rum-next.core
  :target-path "target/%s"
  :aliases {"fig"       ["trampoline" "run" "-m" "figwheel.main"]
            "build-dev" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]}
  :profiles {:dev     {:dependencies [[com.bhauman/figwheel-main "0.2.13"]
                                      [cider/piggieback "0.5.2"]
                                      [javax.xml.bind/jaxb-api "2.3.0"]
                                      [com.sun.xml.bind/jaxb-core "2.3.0"]
                                      [com.sun.xml.bind/jaxb-impl "2.3.0"]]
                       :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]
                                      :init-ns          user}}
             :uberjar {:aot :all}})
