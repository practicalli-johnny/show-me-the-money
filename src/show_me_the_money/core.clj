(ns show-me-the-money.core)

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
