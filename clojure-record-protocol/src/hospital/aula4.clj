(ns hospital.aula4
  (:use clojure.pprint))

(defrecord PacienteParticular [id, nome, nascimento, situacao])
(defrecord PacientePlanoDeSaude [id, nome, nascimento, situacao, plano])

; deve-assinar-pre-autorizacao?
; >= 50; plano

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [paciente, procedimento, valor]))

(defn nao-eh-urgente? [paciente]
  (not= :urgente (:situacao paciente :normal)))

(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor ]
    (and (>= valor 50) (nao-eh-urgente? paciente))))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor ]
    (let [plano (:plano paciente)]
      (and (not (some #(= % procedimento) plano)) (nao-eh-urgente? paciente)))))


(let [particular (->PacienteParticular 15, "Guilherme", "18/09/1981", :normal)
      plano (->PacientePlanoDeSaude 15, "Guilherme", "18/09/1981", :normal,  [:raio-x, :ultrasom ])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 40))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 499990))
  (pprint (deve-assinar-pre-autorizacao? plano, :coleta-de-sangue, 499990)))

(let [particular (->PacienteParticular 15, "Guilherme", "18/09/1981", :urgente)
      plano (->PacientePlanoDeSaude 15, "Guilherme", "18/09/1981", :urgente,  [:raio-x, :ultrasom ])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 40))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 499990))
  (pprint (deve-assinar-pre-autorizacao? plano, :coleta-de-sangue, 499990)))

; nao se coloca multi no final, vou manter no mesmo arquivo
; para que fique facil para comprar as duas implementacoes
(defmulti deve-assinar-pre-autorizacao-multi? class)

(defmethod deve-assinar-pre-autorizacao-multi? PacienteParticular [paciente]
  (println "invocando paciente particular")
  true)
(defmethod deve-assinar-pre-autorizacao-multi? PacientePlanoDeSaude [paciente]
  (println "invocando paciente plano de saude")
  false)

(let [particular (->PacienteParticular 15, "Guilherme", "18/09/1981", :urgente)
      plano (->PacientePlanoDeSaude 15, "Guilherme", "18/09/1981", :urgente,  [:raio-x, :ultrasom ])]
  (pprint (deve-assinar-pre-autorizacao-multi? particular))
  (pprint (deve-assinar-pre-autorizacao-multi? plano)))


; explorando como funciona a funcao que define a estretegia de um defmulti
(defn minha-funcao [p]
  (println p)
  (class p))

(defmulti multi-teste? minha-funcao)
;(multi-teste? "guilherme")
;(multi-teste? "guilherme")


























