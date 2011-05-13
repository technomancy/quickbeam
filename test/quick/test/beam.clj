(ns quick.test.beam
  (:use [quick.beam])
  (:use [clojure.test]))

(deftest test-history
  (is (seq (history)))
  (is (seq (history ".git")))
  (is (not (history "/tmp")))
  (doseq [c (history)]
    (is (every? c [:author :message :sha :date]))))

(deftest test-absolutize
  (is (re-find #"(/home|/Users)/\w+/src/quickbeam"
               (absolutize "~/src/quickbeam"))))
