(ns temp-converter.core
    (:require
     [temp-converter.model]
     [reagent.core :as r]
     [reagent.dom :as d]))

(defonce app-state (r/atom {:unit-from "f" :unit-to "c" :temperature "0"}))

;; -------------------------
;; Views

(defn radio-option [value name]
  (let [id (str name "-" value)
        label (get temp-converter.model/temperature-unit-name-map value)]
    [:div.radio-option
     [:input {
              :type "radio"
              :name name
              :value value
              :id id
              :checked (= value (get @app-state (keyword name)))
              :on-change #(swap! app-state assoc (keyword name) (-> % .-target .-value))}]
     [:label {:for id} label]]))

(defn unit-control [label radio-name]
  (let [temp-units (keys temp-converter.model/converter-map)]
    [:div.unit-control
     [:p label]
     (for [tu temp-units]
       ^{:key (str radio-name "-" tu)}
       [radio-option tu radio-name])]))

(defn user-input []
  [:div.user-input
   [unit-control "Convert from" "unit-from"]
   [unit-control "Convert to" "unit-to"]])

(defn temp-control []
  [:div.temp-control
   [:label {:for "temp"} "Temperature"]
   [:input {:type "number"
            :id "temp"
            :name "temp"
            :value (:temperature @app-state)
            :on-change #(swap! app-state assoc :temperature (-> % .-target .-value))}]])

(defn converted-output []
  (let [s @app-state
        unit-from (:unit-from s)
        unit-to (:unit-to s)
        temperature (:temperature s)]
    [:div.converted-output
     [:h3 "Converted Value"
      [:span#temp-out (temp-converter.model/convert-temperature temperature unit-from unit-to)]
      [:span#unit-out (.toUpperCase unit-to)]]]))

(defn temp-converter-page []
  [:div 
   [:h1 "Temp Converter"]
   [user-input]
   [temp-control]
   [converted-output]])

;; -------------------------
;; Initialize app
(defn mount-root []
  (d/render [temp-converter-page] (.getElementById js/document "app")))

(defn ^:export init! [] 
  (mount-root)) 