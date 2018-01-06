(defproject sag "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/clojurescript "1.9.946"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cljsbuild "1.1.7"]]
  :ring {:handler sag.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}}
  :cljsbuild {
    :builds [{
       :source-paths ["src-cljs"]
       :compiler {
         :output-to "resources/public/main.js"
         :optimizations :whitespace
         :pretty-print true}}]}
  )
