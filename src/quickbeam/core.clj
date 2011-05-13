(ns quickbeam.core
  (:import (org.eclipse.jgit.lib Repository)
           (org.eclipse.jgit.storage.file FileRepository)
           (org.eclipse.jgit.revwalk RevWalk)))

(defn make-repository [path]
  )


(defn debugger [a b c]
  (swank.core/break))
