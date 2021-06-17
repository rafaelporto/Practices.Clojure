(ns loja.aula1)


;["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"]
;{ "guilherme" 37, "paulo" 39 }
;'(1 2 3 4 5)
;[[0 1]]
;#{}

; map
; reduce
; filter

; loop
; for

(map println ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])


(println "\n\n\n\nMEU MAPA")

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (funcao primeiro)
    (meu-mapa funcao (rest sequencia))))

;(meu-mapa println ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if primeiro
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(println "\n\n\n\nMEU MAPA com parada")
(meu-mapa println ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])
(meu-mapa println ["daniela" false "carlos" "paulo" "lucia" "ana"])

(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (meu-mapa funcao (rest sequencia))))))

(println "\n\n\n\nMEU MAPA com  no nil")
(meu-mapa println ["daniela" "guilherme" "carlos" "paulo" "lucia" "ana"])
(meu-mapa println ["daniela" false "carlos" "paulo" "lucia" "ana"])
(meu-mapa println [])
(meu-mapa println nil)

;(meu-mapa println (range 100000))


;TAIL RECURSION
(defn meu-mapa
  [funcao sequencia]
  (let [primeiro (first sequencia)]
    (if (not (nil? primeiro))
      (do
        (funcao primeiro)
        (recur funcao (rest sequencia))))))

(meu-mapa println (range 10000))
