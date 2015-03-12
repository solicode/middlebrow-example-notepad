(ns middlebrow-example-notepad.setup
  (:require [middlebrow-example-notepad.core]
            [figwheel.client :as figwheel]
            [weasel.repl :as repl]))

(enable-console-print!)

(figwheel/start {:websocket-url "ws://localhost:3449/figwheel-ws"})

(when-not (repl/alive?)
  (repl/connect "ws://localhost:9001"))