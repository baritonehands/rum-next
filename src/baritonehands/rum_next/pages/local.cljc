(ns baritonehands.rum-next.pages.local
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(def initial-state
  {:name     ""
   :address1 ""})

(rum/defcs page < (rum/local initial-state ::form)
  [state props]
  (let [form (::form state)]
    [:div
     [:div
      [:label "Full Name"]
      " "
      [:input {:type          "text"
               :default-value (:name props)
               :on-input      #(swap! form assoc :name (-> % .-target .-value))}]]
     [:div
      [:label "Address"]
      " "
      [:input {:type          "text"
               :default-value (:address1 props)
               :on-input      #(swap! form assoc :address1 (-> % .-target .-value))}]]

     [:div
      [:h4 "Entered Data:"]
      [:div (-> @form :name)]
      [:div (-> @form :address1)]]

     (footer/view)]))

