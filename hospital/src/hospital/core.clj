(ns hospital.core
  (:use [clojure pprint])
  (:require [hospital.model :as h.model]))

;espera FILA DE ESPERA
;laboratorio1
;laboratorio2
;laboratorio3

(let [hospital-do-gui (h.model/novo-hospital)]
  (pprint hospital-do-gui))