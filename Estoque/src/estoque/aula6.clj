(ns estoque.aula6)

(def pedido {:mochila { :quantidade 2, :preco 80}
             :camiseta {:quantidade 3, :preco 40}})

(defn imprime-e-15 [valor]
  (println "valor" (class valor) valor)
  15)

(println (map imprime-e-15 pedido))

(defn imprime-e-15 [[chave valor]]
  (println chave "e" valor)
  15)

(println (map imprime-e-15 pedido))

(defn preco-dos-produto [[chave valor]]
  (* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produto pedido))
(println (reduce + (map preco-dos-produto pedido)))

(defn preco-dos-produto [[_ valor]]
(* (:quantidade valor) (:preco valor)))

(println (map preco-dos-produto pedido))
(println (reduce + (map preco-dos-produto pedido)))

(defn total-do-pedido
  [pedido]
  (reduce + (map preco-dos-produto pedido)))

(println "Total do pedido:" (total-do-pedido pedido))

(defn total-do-pedido
  [pedido]
  (->> pedido
      (map preco-dos-produto)
      (reduce +)))

(println "Total do pedido:" (total-do-pedido pedido))

(defn preco-total-do-produto [produto]
  (* (:quantidade produto) (:preco produto)))

(defn total-do-pedido
  [pedido]
  (->> pedido
       vals
       (map preco-total-do-produto)
       (reduce +)))

(println "Total do pedido:" (total-do-pedido pedido))

(def pedido {:mochila { :quantidade 2, :preco 80 }
             :camiseta { :quantidade 3, :preco 40 }
             :chaveiro { :quantidade 1 }})

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println "Filtrando gratuitos")
(println (filter gratuito? (vals pedido)))

(defn gratuito?
  [[chave item]]
  (<= (get item :preco 0) 0))

(println "Filtrando desconstruct gratuitos")
(println (filter gratuito? pedido))

(defn gratuito?
  [item]
  (<= (get item :preco 0) 0))

(println "Filtrando inline gratuitos")
(println (filter (fn [[chave item]] (gratuito? item)) pedido))

(println "Filtrando lambda gratuitos")
(println (filter #(gratuito? (second %)) pedido))

(defn pago?
  [item]
  (not (gratuito? item)))

(println (pago? {:preco 50}))
(println (pago? {:preco 0}))

(println "pago?" ((comp not gratuito?) {:preco 50}))

(def pago? (comp not gratuito?))
(println "pago?" (pago? { :preco 50}))
(println "pago?" (pago? { :preco 0}))




