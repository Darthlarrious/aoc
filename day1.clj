(require ['clojure.string :as 'str])

(defn get-lines [file]
(str/split-lines (slurp file)))

(def inputs (map #(Integer/parseInt %)(get-lines "day1_input.txt")))

(def day1q1 (reduce + inputs))

(defn findFreq [inputs]
  (loop [xs (cycle inputs) found #{} total 0]
    (if (contains? found total)
      total
      (recur (rest xs) (conj found total) (+ total (first xs))))))

(println "calibrating at...." day1q1)
(println "first temporal loop..." (findFreq inputs))
