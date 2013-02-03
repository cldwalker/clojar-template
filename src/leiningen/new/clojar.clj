(ns leiningen.new.clojar
  (:use [leiningen.new.templates :only [renderer name-to-path ->files
                                        multi-segment sanitize-ns]]))

(def render (renderer "clojar"))

(defn clojar
  "This template generates my version of the default template."
  [name]
  (let [main-ns (multi-segment (sanitize-ns name))
        data {:name name
              :namespace main-ns
              :nested-dirs (name-to-path main-ns)}]
    (println "Generating a new clojar project called" name "...")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["CONTRIBUTING.md" (render "CONTRIBUTING.md" data)]
             ["LICENSE.txt" (render "LICENSE.txt" data)]
             [".gitignore" (render "gitignore" data)]
             ["src/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/{{nested-dirs}}_test.clj" (render "test.clj" data)])))
