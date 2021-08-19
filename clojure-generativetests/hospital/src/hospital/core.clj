(ns hospital.core
  (:require [clojure.test.check.generators :as gen]))

;; usando vírgula somente para deixar claro a QUANTIDADE DE SAMPLES
;(println (gen/sample gen/boolean, 100))
;(println (gen/sample gen/small-integer, 100))
;(println (gen/sample gen/string))
;(println (gen/sample gen/string-alphanumeric, 100))
;
;; não usei vírgula de proposito também para indicar os parametros do vetor
;; só para ficar claro educacionalmente, na pratica, arrancaria as vírgulas
;(println (gen/sample (gen/vector gen/small-integer 15), 5))
;(println (gen/sample (gen/vector gen/small-integer 1 5), 100))
;(println (gen/sample (gen/vector gen/small-integer), 100))