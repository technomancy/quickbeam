(ns quickbeam.core
  (:require [clojure.java.io :as jio])
  (:import (org.eclipse.jgit.lib Repository)
           (org.eclipse.jgit.storage.file FileRepository)
           (org.eclipse.jgit.lib ObjectId)
           (org.eclipse.jgit.revwalk RevWalk)))

(defn find-repo [path]
  (->> (.getAbsoluteFile (jio/file path))
       (iterate #(.getParentFile %))
       (take-while identity)
       (map #(file % ".git"))
       (filter #(.exists %))
       first
       FileRepository.))

(defn history [repo-path]
  (let [repo (find-repo repo-path)
        walk (RevWalk. repo)
        id (.resolve repo "HEAD")
        commit (.parseCommit walk id)]
    (.markStart walk commit)
    (iterator-seq (.iterator walk))))
