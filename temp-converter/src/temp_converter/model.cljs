(ns temp-converter.model)

(defn f->c [deg-f]
  (/ (- deg-f 32) 1.8))

(defn c->f [deg-c]
  (+ (* deg-c 1.8) 32))

(def temperature-unit-name-map
  {"f" "Fahrenheit"
   "c" "Celcius"})

(def converter-map 
  {"f" {"c" f->c}
   "c" {"f" c->f}})

(defn convert-temperature [value from to]
  (let [temp-num (js/parseInt value)]
    (if (js/isNaN temp-num)
      "-"
      (if (= from to)
        temp-num
        (.toFixed ((get-in converter-map [from to]) temp-num) 2)))))
