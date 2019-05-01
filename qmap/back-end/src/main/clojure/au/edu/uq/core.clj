(ns au.edu.uq.core
  (:import (java.text SimpleDateFormat)
           (clojure.lang IPersistentMap)
           (au.edu.uq.domains Expense)))
(defn new-expense [date-string dollars cents category merchant-name]
  {:date           (.parse (SimpleDateFormat. "yyyy-MM-dd") date-string)
   :amount-dollars dollars
   :amount-cents   cents
   :category       category
   :merchant-name  merchant-name})
;(defmulti total-cents class)
;(defmethod total-cents IPersistentMap [e]
;  (-> (:amount-dollars e)
;      (* 100)
;      (+ (:amount-cents e))))
;(defmethod total-cents Expense [e]
;  (.amountInCents e))

;(defn total-amount
;  ([expense-list]
;   (total-amount (constantly true) expense-list))
;  ([pred expense-list]
;   (->> expense-list
;        (filter pred)
;        (map total-cents)
;        (apply +)))
;  )
;
;(defmulti is-category? (fn [e _] (class e)))
;(defmethod is-category? IPersistentMap [e some-category]
;  (= (:category e) some-category))
;(defmethod is-category? Expense [e some-category]
;  (= (.getCategory e) some-category))


;(defn category-is [category]
;  #(is-category? % category))

(defprotocol ExpenseCalculation
  (total-cents [e])
  (is-category? [e category]))


(extend-protocol ExpenseCalculation
  Expense
  (total-cents [e]
    (.amountInCents e))
  (is-category? [e some-category]
    (= (.getCategory e) some-category))
                 IPersistentMap
  (total-cents [e]
    (-> (:amount-dollars e)
        (* 100)
        (+ (:amount-cents e))))
  (is-category? [e some-category]
    (= (:category e) some-category))
  )

(defn category-is [category]
  #(is-category? % category))

(defn total-amount
  ([expenses-list]
   (total-amount (constantly true) expenses-list))
  ([pred expenses-list]
   (->> expenses-list
        (filter pred)
        (map total-cents)
        (apply +))))
(defrecord NewExpense [date amount-dollars amount-cents category merchant-name])

(defn hello-from-clojure []
  "Hello from clojure")
