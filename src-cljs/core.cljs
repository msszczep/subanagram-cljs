(ns sag.core
  (:require [clojure.string :as cs]
            [sag.words :as w]))


(def word-to-use 
  (.getElementById js/document "subanagram-input"))


(def button-to-use 
  (.getElementById js/document "find-subanagrams"))


(def subanagram-results-field
  (.getElementById js/document "subanagram-results"))


(defn subanagram-generator [submitted-word]
  (let [submitted-word-freqs 
         (merge (zipmap "abcdefghijklmnopqrstuvwxyz" 
                        (repeat 26 0))
                (frequencies (cs/lower-case submitted-word)))]
    (->> w/words
         (filter (fn [word] 
                   (let [lc-word (cs/lower-case word)]
                     (every? #(<= ((frequencies lc-word) %)
                                  (submitted-word-freqs %)) 
                       (set lc-word)))))
         (group-by count)
         sort)))


(defn format-subanagram-output [subanagram-data]
  (apply str
    (conj
      (vec
        (for [[n words-to-use] subanagram-data]
          (str "<B>Words that are " n " letter" 
               (if (= 1 n) "" "s") 
               " long</B>:<BR>" 
               (apply str (map #(str % "<br>") words-to-use))
               "Number of " n " letter-words: <B>" 
               (count words-to-use)
               "</B><P></P>")))
      (->> subanagram-data 
           vals 
           (map count) 
           (apply +)) 
      " total words were found.")))


(defn find-subanagrams! []
  (set! (.-innerHTML subanagram-results-field) 
        (-> word-to-use
            .-value
            subanagram-generator
            format-subanagram-output)))


(set! (.-onclick button-to-use) find-subanagrams!)
