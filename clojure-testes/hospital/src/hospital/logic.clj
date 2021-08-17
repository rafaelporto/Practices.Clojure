(ns hospital.logic)

; Test Driven Development
; Test Driven Design

;(defn cabe-na-fila?
;  [hospital departamento]
;    (-> hospital
;        departamento
;        count
;        (< 5)))

; funciona para o caso de não ter o departamento
;(defn cabe-na-fila?
;  [hospital departamento]
;  (when-let [fila (get hospital departamento)]
;    (-> fila
;        count
;        (< 5))))

; também funciona, mas usa o some->
; desvantagem/vantagem "menos explicito"
; desvantagem qualquer um que der nil, devolve nil
(defn cabe-na-fila?
  [hospital departamento]
    (some-> hospital
        departamento
        count
        (< 5)))

;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (ex-info "Não cabe ninguém neste departamento." {:paciente pessoa}))))

;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (IllegalStateException. "Não cabe ninguém neste departamento." ))))

; nil???
;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)))

;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (IllegalStateException. "Não cabe ninguém neste departamento." ))))

; exemplo para extrair com ex-data
;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (ex-info "Não cabe ninguém neste departamento." {:paciente pessoa, :tipo :impossivel-colocar-pessoa-na-fila}))))


;(defn- tenta-colocar-na-fila
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)))
;
;(defn chega-em
;  [hospital departamento pessoa]
;  (if-let [novo-hospital (tenta-colocar-na-fila hospital departamento pessoa)]
;    { :hospital novo-hospital, :resultado :sucesso }
;    { :hospital hospital, :resultado :impossivel-colocar-pessoa-na-fila }
;    ))

; antes de fazer swap chega-em vai ter que tratar o resultado
; não dá para fugir disso (preocupações), se o resultado é para ser usado com atomos ou similares
; e ao mesmo tempo tratar erros
;(defn chega-em!
;  [hospital departamento pessoa]
;  (chega-em hospital departamento pessoa))

(defn chega-em
  [hospital departamento pessoa]
  (if (cabe-na-fila? hospital departamento)
    (update hospital departamento conj pessoa)
    (throw (ex-info "Não cabe ninguém neste departamento." {:paciente pessoa}))))

; código de um curso anterior

(defn atende
  [hospital departamento]
  (update hospital departamento pop))

(defn proxima
  "Retorna o próximo paciente da fila"
  [hospital departamento]
  (-> hospital
      departamento
      peek))

(defn transfere
  "Transfere o próximo paciente da fila de para a fila para"
  [hospital de para]
  (let [pessoa (peek (get hospital de))]
    (-> hospital
        (atende de)
        (chega-em para pessoa))))















