(ns baritonehands.rum-next.pages
  (:require
    [baritonehands.rum-next.pages.index :as index]
    #?@(:clj [[baritonehands.rum-next.pages.server :refer [defpage]]]))
  #?(:cljs
     (:require-macros [baritonehands.rum-next.pages.client :refer [defpage]])))

(defpage index index/root)
