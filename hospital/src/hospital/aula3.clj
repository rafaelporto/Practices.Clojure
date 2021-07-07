(ns hospital.aula3
  (:use [clojure pprint])
  (:require [hospital.logic :as h.logic]
            [hospital.model :as h.model]))

;simbolo que qq thread que acessar esse namespace vai ter acesso a ele como valor padrao guilherme
(def nome "guilherme")

;redefinir o simbolo (refiz o binding)
(def nome 32567)

(let [nome "guilherme"]
  ;coisa 1
  ;coisa 2
  (println nome)
  ;nao estou refazendo o biding do simbolo local
  ;criando um novo simbolo local e este bloco e ESCONDENDO o anterior
  ;SHADOWING
  (let [nome "daniela"]
    ;coisa 3
    ;coisa 4
    (println nome)
    )
  (println nome))

(defn teste-atomao []
  (let [hospital-silveira (atom { :espera h.model/fila-vazia })]
    (println hospital-silveira)
    (pprint hospital-silveira)
    (pprint (deref hospital-silveira))
    (pprint @hospital-silveira)

    ;nao eh assim que eu altero conteudo dentro de um atomo
    (assoc @hospital-silveira :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)

    ;essa é (uma das) maneiras de alterar conteudo dentro de um atomo
    (swap! hospital-silveira assoc :laboratorio1 h.model/fila-vazia)
    (pprint @hospital-silveira)

    (swap! hospital-silveira assoc :laboratorio2 h.model/fila-vazia)
    (pprint @hospital-silveira)

    ;update tradicional imutavel, com dereferencia, que nao trara efeito colatoral
    (update @hospital-silveira :laboratorio1 conj "111")

    ;indo pra swap
    (swap! hospital-silveira update :laboratorio1 conj "111")
    (pprint hospital-silveira)
    ))

;(teste-atomao)



(defn chega-em-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em-pausado-logando :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-em-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-em-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

;(simula-um-dia-em-paralelo)

(defn chega-sem-malvado! [hospital pessoa]
  (swap! hospital h.logic/chega-em :espera pessoa)
  (println "apos inserir" pessoa))

(defn simula-um-dia-em-paralelo
  []
  (let [hospital (atom (h.model/novo-hospital))]
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "111"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "222"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "333"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "444"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "555"))))
    (.start (Thread. (fn [] (chega-sem-malvado! hospital "666"))))
    (.start (Thread. (fn [] (Thread/sleep 4000)
                       (pprint hospital))))))

(simula-um-dia-em-paralelo)