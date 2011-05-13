(ns quick.beam
  (:require [clojure.java.io :as io])
  (:import (java.util Date)
           (org.eclipse.jgit.revwalk RevWalk)
           (org.eclipse.jgit.storage.file FileRepository)))

(defn find-repo [path]
  (when-let [git-dir (->> (.getAbsoluteFile (io/file path))
                          (iterate #(.getParentFile %))
                          (take-while identity)
                          (map #(io/file % ".git"))
                          (filter #(.exists %))
                          first)]
    (FileRepository. git-dir)))

(defn commit-map [commit]
  (let [{:keys [authorIdent fullMessage name commitTime]} (bean commit)]
    {:author (.toExternalString authorIdent)
     :message fullMessage
     :sha name
     :date (Date. (long (* 1000 commitTime)))}))

(defn history
  ([repo-path]
     (if-let [repo (find-repo repo-path)]
       (let [walk (RevWalk. repo)
             id (.resolve repo "HEAD")
             commit (.parseCommit walk id)]
         (.markStart walk commit)
         (map commit-map (iterator-seq (.iterator walk))))))
  ([] (history "")))
