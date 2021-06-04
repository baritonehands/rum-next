(ns baritonehands.rum-next.pages
  (:require
    [baritonehands.rum-next.pages.index :as index]
    [baritonehands.rum-next.pages.reactive :as reactive]
    [baritonehands.rum-next.pages.local :as local]
    #?@(:clj [[baritonehands.rum-next.pages.server :refer [page-handler]]])))
;#?(:cljs
;   (:require-macros [baritonehands.rum-next.pages.client :refer [defpage]])))


(def index (page-handler index/root (constantly {:foo "bar"})))
(def reactive (page-handler reactive/page (constantly {:counter 10})))
(def local (page-handler local/page (constantly {:name     "Brian Gregg"
                                                 :address1 "123 Main St."})))
