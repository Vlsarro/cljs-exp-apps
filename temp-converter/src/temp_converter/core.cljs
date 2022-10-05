(ns temp-converter.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as d]))

;; -------------------------
;; Views

(defn user-input []
  [:div.user-input
   [:div.unit-control
    [:p "Convert from"] 
    [:div.radio-option
     [:input#unit-c {:type "radio" :name "unit" :value "c" :checked "checked"}]
     [:label {:for "unit-c"} "Celcius"]]
    [:div.radio-option
     [:input#unit-f {:type "radio" :name "unit" :value "f"}]
     [:label {:for "unit-f"} "Fahreheit"]]]])

(defn temp-control []
  [:div.temp-control
   [:label {:for "temp"} "Temperature"]
   [:input {:type "number" :id "temp" :name "temp"}]])

(defn converted-output []
  [:div.converted-output
   [:h3 "Converted Value"
    [:span#temp-out "-"]
    [:span#unit-out "F"]]])

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