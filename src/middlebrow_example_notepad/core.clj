(ns middlebrow-example-notepad.core
  (:require [middlebrow-example-notepad.server :as server]
            [middlebrow.core :as mb]
            ; You wouldn't normally need to require all 3 containers like this (only
            ; requiring the one you need is fine), but just the sake of convenience and
            ; the ability to swap between them easily, let's include all of them for this
            ; example project.
            [middlebrow-fx.core :as fx]
            [middlebrow-swt.core :as swt]
            [middlebrow-thrust.core :as thrust])
  (:gen-class))

(defonce server nil)

(defn start-server []
  "Starts the server. If one is already running, it will be restarted."
  (alter-var-root #'server
    (fn [prev]
      (when prev (server/stop server))
      (server/start))))

(defn open-window []
  (let [window (fx/create-window ; Or use `swt/create-window` or `thrust/create-window` instead.
                 :url "http://localhost:7171" ; URL to your web app
                 :width 500
                 :height 400
                 :title "Notepad")]
    (mb/listen-closed window
      (fn [e]
        (when (mb/container-of? window :thrust)
          (thrust/destroy-process-of window))
        ; Note: You can call `(System/exit 0)` here if you'd like closing the window to exit
        ; the program entirely.
        ))

    (mb/listen-focus-gained window
      (fn [e]
        (println "Window gained focus")))

    (mb/show window)
    (mb/activate window)

    ; Needing to start a UI loop explicitly is only required for SWT.
    (when (mb/container-of? window :swt)
      (swt/start-ui-loop window))))

(defn -main [& [command]]
  (start-server)
  (when (not= command "headless")
    (open-window)))