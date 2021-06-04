(ns baritonehands.rum-next.pages.reactive
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(defonce my-counter (atom 0))

(rum/defc page < rum/reactive [props]
  (let [counter (rum/react my-counter)]
    [:div
     [:button {:type     "button"
               :on-click #(swap! my-counter inc)}
      "Clicked " counter " times!"]
     [:button {:type "button"
               :on-click #(reset! my-counter 0)}
      "Reset"]
     (footer/view)]))
