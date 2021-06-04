(ns baritonehands.rum-next.http)

(defonce debug-delay (atom 0))

(def default-opts
  {:headers {:accept "application/json"}})

(defprotocol Fetch
  (abort [_])
  (ready [_]))

(defn fetch
  ([url] (fetch url {}))
  ([url opts]
   (let [controller (js/AbortController.)
         signal (.-signal controller)
         promise (.fetch js/window url (-> (merge-with into default-opts opts)
                                           (assoc :signal signal)
                                           (cond->
                                             (pos? @debug-delay) (assoc-in [:headers :x-debug-delay] @debug-delay))
                                           (clj->js)))]
     (reify Fetch
       (abort [_] (.abort controller))
       (ready [_] promise)))))

(defn json->clj [resp]
  (-> (ready resp)
      (.then #(.json %))
      (.then #(js->clj % :keywordize-keys true))))
