(ns quickbeam.test.core
  (:use [quickbeam.core])
  (:use [clojure.test]))

(deftest test-history
  (is (seq (history)))
  (is (seq (history ".git")))
  (is (not (history "/tmp")))
  (doseq [c (history)]
    (is (every? c [:author :message :sha :date]))))
