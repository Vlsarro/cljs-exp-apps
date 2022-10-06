(ns temp-converter.model)

(defn f->c [deg-f]
  (/ (- deg-f 32) 1.8))

(defn c->f [deg-c]
  (+ (* deg-c 1.8) 32))

(def converter-map 
  {"f" {"c" f->c}
   "c" {"f" c->f}})

(defn convert-temperature [value from to]
  (.toFixed ((get-in converter-map [from to]) (js/parseInt value)) 2))
