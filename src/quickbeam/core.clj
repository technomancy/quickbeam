(ns quickbeam.core
  (:require [clojure.java.io :as jio])
  (:import (org.eclipse.jgit.lib Repository)
           (org.eclipse.jgit.storage.file FileRepository)
           (org.eclipse.jgit.lib ObjectId)
           (org.eclipse.jgit.revwalk RevWalk)))

(defn find-repo [path]
  (when-let [git-dir (->> (.getAbsoluteFile (jio/file path))
                          (iterate #(.getParentFile %))
                          (take-while identity)
                          (map #(file % ".git"))
                          (filter #(.exists %))
                          first)]
    (FileRepository. git-dir)))

(defn commit-map [commit]
  (let [{:keys [authorIdent fullMessage name commitTime]} (bean commit)]
    {:author (.toExternalString authorIdent)
     :message fullMessage
     :sha name
     :date (java.util.Date. (long (* 1000 commitTime)))}))

(defn history
  ([repo-path]
     (if-let [repo (find-repo repo-path)]
       (let [walk (RevWalk. repo)
             id (.resolve repo "HEAD")
             commit (.parseCommit walk id)]
         (.markStart walk commit)
         (map commit-map (iterator-seq (.iterator walk))))))
  ([] (history "")))
