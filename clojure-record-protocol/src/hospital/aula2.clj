(ns hospital.aula2
  (:use clojure.pprint))

(defrecord Paciente [ id, nome, nascimento ])

; Paciente plano de Saude ===> + plano de saude
; Paciente Particular ===> + 0

; caminho horripilante com provaveis problemas horriveis e tipos 2ˆn
;(defrecord PacientePlanoSaude HERDA Paciente [plano])

; digitacao nao eh o maior problema da nossa vida
(defrecord PacienteParticular [ id, nome, nascimento ])
(defrecord PacientePlanoDeSaude [ id, nome, nascimento, plano ])

; REGRAS DIFERENTES PARA TIPOS DIFERENTES
;deve-assinar-pre-autorizacao?
; Particular ==> valor >= 50
; PlanoDeSaude ==> procedimento NAO esta no plano

; VANTAGEM: tudo no mesmo lugar
; DESVANTAGEM: tudo no mesmo lugar
;(defn deve-assinar-pre-autorizacao? [paciente procedimento valor]
;  (if (= PacienteParticular (type paciente))
;    (>= valor 50)
;    ; assumindo que existe os dois tipos
;    (if (= PacientePlanoDeSaude (type paciente))
;      (let [plano (get paciente :plano)]
;        (not (some #(= % procedimento) plano)))
;      true)))

(defprotocol Cobravel
  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor]))

(extend-type PacienteParticular
  Cobravel
  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor ]
    (>= valor 50)))

(extend-type PacientePlanoDeSaude
  Cobravel
  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor ]
    (let [plano (:plano paciente)]
      (not (some #(= % procedimento) plano)))))
;contains? => verifica o indice... e indice voce fica dependente da estrutuda de dados... vetor é numerico

;alternativa seria implementar diretamente
;(defrecord PacientePlanoDeSaude [ id, nome, nascimento, plano ]
;  Cobravel
;  (deve-assinar-pre-autorizacao? [ paciente, procedimento, valor ]
;    (let [plano (:plano paciente)]
;      (not (some #(= % procedimento) plano)))))

(let [particular (->PacienteParticular 15, "Guilherme", "18/09/1981")
      plano (->PacientePlanoDeSaude 15, "Guilherme", "18/09/1981", [:raio-x, :ultrasom ])]
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 500))
  (pprint (deve-assinar-pre-autorizacao? particular, :raio-x, 40))
  (pprint (deve-assinar-pre-autorizacao? plano, :raio-x, 499990))
  (pprint (deve-assinar-pre-autorizacao? plano, :coleta-de-sangue, 499990))
  )


; uma variacao baseada na plastra a seguir, mas com extend-type e com gregorian calendar
; From Sean Devlin's talk on protocols at Clojure Conj

(defprotocol Dateable
  (to-ms [this]))

(extend-type java.lang.Number
  Dateable
  (to-ms [this] this))

(pprint (to-ms 56))

(extend-type java.util.Date
  Dateable
  (to-ms [this] (.getTime this)))

(pprint (to-ms (java.util.Date.)))

(extend-type java.util.Calendar
  Dateable
  (to-ms [this] (to-ms (.getTime this))))

(pprint (to-ms (java.util.GregorianCalendar.)))

;(pprint (to-ms "Guilherme"))

















