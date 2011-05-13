(ns quickbeam.test.core
  (:use [quickbeam.core])
  (:use [clojure.test]))

(deftest test-history
  (is (seq (history "."))))
