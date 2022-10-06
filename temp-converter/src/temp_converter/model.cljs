(ns temp-converter.model)

(def k-delta 273.15)

(defn f->c [deg-f]
  (/ (- deg-f 32) 1.8))

(defn f->k [deg-f]
  (+ k-delta (/ (- deg-f 32) 1.8)))

(defn c->f [deg-c]
  (+ (* deg-c 1.8) 32))

(defn c->k [deg-c]
  (+ deg-c k-delta))

(defn k->f [deg-k]
  (+ (* (- deg-k k-delta) 1.8) 32))

(defn k->c [deg-k]
  (- deg-k k-delta))


(def temperature-unit-name-map
  {"f" "Fahrenheit"
   "c" "Celcius"
   "k" "Kelvin"})

(def converter-map 
  {"f" {"c" f->c
        "k" f->k}
   "c" {"f" c->f
        "k" c->k}
   "k" {"f" k->f
        "c" k->c}})

(defn convert-temperature [value from to]
  (let [temp-num (js/parseFloat value)]
    (if (js/isNaN temp-num)
      "-"
      (if (= from to)
        temp-num
        (.toFixed ((get-in converter-map [from to]) temp-num) 2)))))
