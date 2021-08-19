(ns hospital.logic-test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [clojure.test.check.generators :as gen]
            [schema.core :as s]))

(s/set-fn-validation! true)

; são testes ESCRITOS baseados em exemplos
(deftest cabe-na-fila?-test

  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera []} :espera)))

  (testing "Que cabe pessoas em filas de tamanho até 4 inclusive"
    (doseq [fila (gen/sample (gen/vector gen/string-alphanumeric 0 4) 100)]
      (is (cabe-na-fila? {:espera fila}, :espera))))

  (testing "Que não cabe na fila quando a fila está cheia"

    (is (not (cabe-na-fila? {:espera [1 5 37 54 21]}, :espera)))
    )

  ; one off da borda do limiete para cima
  (testing "Que não cabe na fila quando tem mais de uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5, 6]}, :espera))))

  (testing "Que cabe na fila quando tem gente, mas não está cheia"
    (is (cabe-na-fila? {:espera [1, 2, 3, 4]}, :espera))
    (is (cabe-na-fila? {:espera [1, 2]}, :espera)))

  (testing "Que não cabe quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4]}, :raio-x)))))
















