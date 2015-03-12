(defproject middlebrow-example-notepad "0.1.0-SNAPSHOT"
  :description "A sample Notepad-like application running on Middlebrow."
  :url "https://github.com/solicode/middlebrow-example-notepad"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3058"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [net.solicode/middlebrow "0.1.0-SNAPSHOT"]
                 [net.solicode/middlebrow-fx "0.1.0-SNAPSHOT"]
                 [net.solicode/middlebrow-swt "0.1.0-SNAPSHOT"]
                 [net.solicode/middlebrow-thrust "0.1.0-SNAPSHOT"]
                 [cheshire "5.4.0"]
                 [compojure "1.3.2"]
                 [ring/ring-defaults "0.1.4"]
                 [ring-middleware-format "0.4.0"]
                 [http-kit "2.1.18"]
                 [prismatic/dommy "1.0.0"]
                 [cljs-ajax "0.3.10"]]
  :main middlebrow-example-notepad.core
  ; Note:  Modify :jvm-opts to include `-XstartOnFirstThread` if you're running SWT on OSX.
  :jvm-opts ["-Dapple.awt.UIElement=true"]
  ;:jvm-opts ["-XstartOnFirstThread" "-Dapple.awt.UIElement=true"]
  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-figwheel "0.2.5-SNAPSHOT"]]
  :source-paths ["src"]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {:builds [{:id           "dev"
                        :source-paths ["src" "dev-src"]
                        :compiler     {:output-to     "resources/public/js/middlebrow-example-notepad.js"
                                       :output-dir    "resources/public/js/compiled/out"
                                       :asset-path    "js/compiled/out"
                                       :optimizations :none
                                       :main          middlebrow-example-notepad.setup
                                       :source-map    true}}
                       {:id           "min"
                        :source-paths ["src"]
                        :compiler     {:output-to     "resources/public/js/middlebrow-example-notepad.js"
                                       :optimizations :advanced
                                       :pretty-print  false}}]}
  :figwheel {:http-server-root "public"
             :server-port      3449
             :css-dirs         ["resources/public/css"]
             :nrepl-port       7272}
  :profiles {:dev     {:dependencies [[figwheel "0.2.5-SNAPSHOT"]
                                      [weasel "0.6.0"]
                                      [com.cemerick/piggieback "0.1.5"]]}
             :uberjar {:aot :all}})
