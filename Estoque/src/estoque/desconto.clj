(ns estoque.desconto)

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for maior que 100"
  [valor-bruto]
  (if (> valor-bruto 100)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))

(println (valor-descontado 100))

;PREDICATE
(defn aplica-desconto?
  [valor-bruto]
  (if (> valor-bruto 100)
    true
    false))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(defn valor-descontado
  "Retorna o valor com desconto de 10% se o valor bruto for maior que 100"
  [valor-bruto]
  (if (aplica-desconto? valor-bruto)
    (let [taxa-de-desconto (/ 10 100)
          desconto (* valor-bruto taxa-de-desconto)]
      (- valor-bruto desconto))
    valor-bruto))

(println (valor-descontado 1000))

(println (valor-descontado 100))

(defn aplica-desconto?
  [valor-bruto]
  (when (> valor-bruto 100)
    (println "Usando When")
    true))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))

(defn aplica-desconto?
  [valor-bruto]
   (> valor-bruto 100))

(println (aplica-desconto? 1000))
(println (aplica-desconto? 100))