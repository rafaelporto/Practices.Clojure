(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]))

(deftest cabe-na-fila?-test

  ; boundary tests
  ; exatamente na borda e one off. -1, +1, <=, >=, =.

  ; checklist na minha cabeça, poderia ser um checklist no papel

  ; borda do zero
  (testing "Que cabe numa fila vazia"
    (is (cabe-na-fila? {:espera []} :espera)))

  ; borda do limite
  (testing "Que não cabe na fila quando a fila está cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5]}, :espera))))

  ; one off da borda do limiete para cima
  (testing "Que não cabe na fila quando tem mais de uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5, 6]}, :espera))))

  (testing "Que cabe na fila quando tem gente, mas não está cheia"
    (is (cabe-na-fila? {:espera [1, 2, 3, 4]}, :espera))
    (is (cabe-na-fila? {:espera [1, 2]}, :espera)))

  (testing "Quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4]}, :raio-x))))
  )