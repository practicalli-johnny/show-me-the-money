(ns show-me-the-money.core)

;; tax map - refactor taxes into one data structure
(def taxes
  {personal-allowance 11000
   :income-tax {:basic 1/5
                :high  40/100
                :additional 45/100}})

;; working with maps & keywords
;; (taxes :personal-allowance)
;; (:personal-allowance taxes)
;; (:basic  (taxes :income-tax))
;; (get taxes :income-tax)
;; (get-in taxes [:income-tax :basic])


;; No taxes on personal allowance
(def personal-allowance 11000)

;; From 11,001 to 43,000
(def income-tax-rate-basic 20/100)

;; Earnings over 43,001
(def income-tax-rate-high 40/100)

;; Earings over 150,000
(def income-tax-rate-additional 45/100)

;; Its not this simple unfortunately...
(def national-insurance-rate 11/100)

(defn basic-tax
  [full-salary]
  (let [taxable-salary (- full-salary personal-allowance)]
    (* taxable-salary income-tax-rate-basic)))

(defn high-taxes
  [full-salary]
  (if (> full-salary 43000)
    (let [taxable-salary (- full-salary 43000)]
      (* taxable-salary income-tax-rate-high))
    0))

(defn apply-tax
  [income]
  (if (<= income personal-allowance)
    income
    (- income (basic-tax income) (high-taxes income))))


(defn monthly-salary
  [full-salary]
  (/ (apply-tax full-salary) 12))

(defn weekly-salary
  [full-salary]
  (/ (apply-tax full-salary) 52))

(defn hourly-salary
  [full-salary]
  (/ (weekly-salary full-salary) 40 ))

(monthly-salary 50000)
;; => 3283.3333
(weekly-salary 50000.0)
;; => 757.6923076923077

(hourly-salary 50000.0)
;; => 18.942307692307693

;; (basic-tax 20000)
;; (high-taxes 20000)

(apply-tax 20000)
(apply-tax 50000)
;; take off personal allowance
;; 20% off from basic

;; simple calculation
(salary-after-taxes 2000)


(* 20000.0 income-tax-rate)
;; (* 1.0 20/100)
;; 20/100 


;; (* taxable-salary national-insurance-rate)


(defn money-i-want
  [money-in-my-pocket]
  (if (<= money-in-my-pocket 43000)
    (+ money-in-my-pocket (* money-in-my-pocket 25/100))
    (+ money-in-my-pocket (* money-in-my-pocket 45/100))))

(money-i-want 40000)
;;=> 50000N

(apply-tax 50000)
;; => 39400N

(money-i-want 50000)
;;=> 72500N

(apply-tax 72500)
;; 50000

