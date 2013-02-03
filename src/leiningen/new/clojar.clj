(ns leiningen.new.clojar
  (:use [leiningen.new.templates :only [renderer name-to-path ->files]]))

(def render (renderer "clojar"))

(defn clojar
  "FIXME: write documentation"
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (->files data
             ["src/{{sanitized}}/foo.clj" (render "foo.clj" data)])))
