(require ['clojure.string :as 'str])
(use 'clojure.data)

(defn get-lines [file]
  (str/split-lines (slurp file)))

(def inputs (get-lines "day2_input.txt"))

(defn has-repeats [input num] 
  (not (empty? (filter #(= (val %) num) (frequencies input)))))

(defn d2q1 [inputs]
  (* (count (filter true? (map #(has-repeats % 2) inputs))) (count (filter true? (map #(has-repeats % 3) inputs)))))

(defn computeDelta [inputa inputb]
  (loop [startat 0 
    delta 0] 
    (if 
      (= startat (count inputa)) 
      delta 
      (if 
        (= (nth inputa startat) (nth inputb startat)) 
        (recur (+ 1 startat) delta)
        (recur (+ 1 startat) (+ 1 delta))))))

(defn getDiffering 
([input] (getDiffering (first input) (second input)))
([inputa inputb](loop [startat 0 
    delta '""] 
    (if 
      (= startat (count inputa)) 
      delta 
      (if 
        (= (nth inputa startat) (nth inputb startat)) 
        (recur (+ 1 startat) (str delta (nth inputa startat)))
        (recur (+ 1 startat) delta))))))

(defn compareDeltas [item items]
  (map #(computeDelta item %) items))

(defn get-boxes [inputs]
  (map 
    #(nth inputs (first %)) 
      (filter 
        #(true? (second %)) 
          (map-indexed 
            #(vector %1 (.contains %2 1)) 
              (map 
                #(compareDeltas % inputs) 
                  inputs)))))

(println "checksum..." (d2q1 inputs))
(println "profiling...")
(time (d2q1 inputs))

(println "letters in common..." (getDiffering (get-boxes inputs)))
(println "profiling..")
(time (getDiffering (get-boxes inputs)))
