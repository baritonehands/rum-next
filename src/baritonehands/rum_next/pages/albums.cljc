(ns baritonehands.rum-next.pages.albums
  (:require [rum.core :as rum]
            [baritonehands.rum-next.components.footer :as footer]))

(defn ms->duration [ms]
  (let [minutes (Math/floor (/ ms 1000 60))
        seconds (Math/floor (mod (/ ms 1000) 60))]
    (str minutes "min " seconds "sec")))

(defn bytes->size [bytes]
  (let [[amount unit] (cond
                        (> bytes (Math/pow 2 30)) [(/ bytes (Math/pow 2 30)) "GB"]
                        (> bytes (Math/pow 2 20)) [(/ bytes (Math/pow 2 20)) "MB"]
                        (> bytes (Math/pow 2 10)) [(/ bytes (Math/pow 2 10)) "KB"]
                        :else [bytes "B"])]
    (str (.toFixed amount 2) unit)))

(rum/defcs detail < (rum/local -1 ::selected) [state {:keys [artist-id artist-name title tracks]}]
  (let [selected (::selected state)]
    [:div
     [:div [:a {:href (str "/artists/" artist-id)} artist-name]]
     [:h2 title]
     [:h3 "Tracks"]
     [:ol
      (for [[idx track] (map-indexed vector tracks)]
        [:li {:key (:track-id track)}
         [:a {:role "button"
              :on-click (fn [event]
                          (reset! selected idx)
                          (.preventDefault event)
                          false)}
          (:name track)]
         (if (= idx @selected)
           [:p {:id (str "track" (inc idx))}
            [:span [:span "Composer:"] " " [:span (:composer track)] [:br]]
            [:span [:span "Duration:"] " " [:span (ms->duration (:milliseconds track))]  [:br]]
            [:span [:span "Size:"] " " [:span (bytes->size (:bytes track))] [:br]]])])]
     (footer/view)]))
