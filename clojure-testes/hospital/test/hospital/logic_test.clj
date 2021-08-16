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

    ; é de simples leitura, pois é sequencial,
    ; mas a desvantagem é que podemos errar em fazer coisas sequenciais
    ;(is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5]}, :espera)))

    ; não precisa ser sequencial e no mundo real não é
    ; portanto faça testes que não são sequênciais
    (is (not (cabe-na-fila? {:espera [1 5 37 54 21]}, :espera)))
    )

  ; one off da borda do limiete para cima
  (testing "Que não cabe na fila quando tem mais de uma fila cheia"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4, 5, 6]}, :espera))))

  (testing "Que cabe na fila quando tem gente, mas não está cheia"
    (is (cabe-na-fila? {:espera [1, 2, 3, 4]}, :espera))
    (is (cabe-na-fila? {:espera [1, 2]}, :espera)))

  (testing "Que não cabe quando o departamento não existe"
    (is (not (cabe-na-fila? {:espera [1, 2, 3, 4]}, :raio-x))))
  )

(deftest chega-em-test

  (testing "aceita pessoas enquanto cabem pessoas na fila"

    ; implentanção ruim, pois testa que escrevemos o que escrevemos,
    ; isto é, testa o que erramos o que erramos e que acertamos o que acertamos
    ;(is (= (update {:espera [1,2]} :espera 5)
    ;       (chega-em {:espera [1,2]}, :espera, 5)))

    (is (= { :espera [1,2,3,4,5] }
           (chega-em {:espera [1, 2, 3, 4]}, :espera, 5)))
    ; teste não sequencial
    (is (= { :espera [1,2,5] }
           (chega-em {:espera [1, 2]}, :espera, 5))))

  (testing "não aceita quando não cabe a fila"
    ; verificando que uma exception foi jogada
    ; código clássico horrível. Usamos uma exception GENERICA.
    ; mas qualquer outro erro genérico vai jogar essa exception, e nós vamos achar que deu certo
    ; quando deu errado
    ;(is (thrown? clojure.lang.ExceptionInfo
    ;             (chega-em {:espera [1 35 42 64 21]}, :espera 76)))

    ; mesmo que eu escolha uma exception do genero, perigoso!
    ;(is (thrown? IllegalStateException
    ;             (chega-em {:espera [1 35 42 64 21]}, :espera 76)))

    ; outra abordagem, do nil
    ; mas o perigo do swap, teriamos que trabalhar em outro ponto a condicao de erro
    ;(is (nil? (chega-em {:espera [1 35 42 64 21]}, :espera 76)))

    )
  )















