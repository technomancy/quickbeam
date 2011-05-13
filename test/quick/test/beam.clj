(ns quick.test.beam
  (:use [quick.beam])
  (:use [clojure.test]))

(deftest test-history
  (is (seq (history)))
  (is (seq (history ".git")))
  (is (not (history "/tmp")))
  (doseq [c (history)]
    (is (every? c [:author :message :sha :date]))))
