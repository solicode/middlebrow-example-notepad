# Middlebrow Notepad (Example)

This is a very basic sample project which shows how one might go about
using [Middlebrow](https://github.com/solicode/middlebrow) to create a simple Notepad-like application.

This project exists simply for learning purposes. It's not meant to be
anything more than that. It also has comments in the source explaining
how some of the things work.

## Overview

This project is written with Clojure (for the sever) and ClojureScript
(for the client). When you run the app, a server is started up at
port 7171 and Middlebrow connects to it to display the app
in its own window.

Middlebrow is merely a container for running the simple web app we created as a
desktop application.

Middlebrow currently has three browser/web-view backends that you can choose from:

* **Thrust**: Chromium-based cross-platform application framework which is designed to be cross-language.
Middlebrow uses the [clj-thrust](https://github.com/solicode/clj-thrust) bindings for its Clojure support.
* **JavaFX**: Using the built-in WebView component, which is based on WebKit.
* **SWT**: Using the built-in Browser widget, which has support for WebKit, Firefox, and IE.

## Getting Started

### Running the app

First, clone this repository:

```
git clone https://github.com/solicode/middlebrow-example-notepad
```

Then simply run it with [Leiningen](http://leiningen.org):

```
lein run
```

This will open the Middlebrow container which hosts our web app. You can also
open it directly in your browser (any modern browser should do) by going
to http://localhost:7171 while the server is running.

#### Additional notes:

##### When using the Thrust container

If you're using the Thrust container, you will need the Thrust runtime in order to run this program. You can download it [here](https://github.com/breach/thrust/releases). By default, clj-thrust looks for the runtime in `$HOME/.thrust/`, but it's possible to specify a different location with `(create-window :thrust-directory custom-location)`.

##### When using the SWT container

SWT requires different dependencies depending on OS and architecture. This project has Leiningen profiles for each case. For example, if you're on Windows with a 64-bit JVM, use the following command:

```
lein with-profile swt-win64 run
```

Here are all the available profiles to choose from:

```
swt-win32
swt-win64
swt-linux32
swt-linux64
swt-mac32
swt-mac64
```

### Development

The `project.clj` file has [cljsbuild](https://github.com/emezeske/lein-cljsbuild), [figwheel](https://github.com/bhauman/lein-figwheel), and [weasel](https://github.com/tomjakubowski/weasel) already set up for you. How you want to set up your workflow is entirely up to you though. The most basic and easiest way to get up and running would probably be to just run cljsbuild with automatic incremental compilation turned on, like so:

```
lein cljsbuild auto dev
```

So any changes you make to your ClojureScript files will automatically be compiled for you upon saving. After compilation, simply refresh your app to see your changes.

Also, feel free to use the browser when you're developing instead of using the Middlebrow window. Either will do, but some people may prefer the browser because they have certain add-ons/extensions that they find indispensable when developing web apps.

#### Figwheel

Figwheel is nice in that it enables live code reloading, meaning you do not have to refresh the page to see your new changes. For more details on what Figwheel is capable of, check out the [project page here](https://github.com/bhauman/lein-figwheel).

To get up and running with Figwheel, run the following command:

```
lein figwheel
```

This should have started up the Figwheel server, so now when you open your web app (in Middlebrow or the web browser), it should be watching for changes in your source code. Once you save a file, those changes will be hot loaded and you should see them immediately! Feels great, doesn't it? (^^) I personally do most of my work this way, but go ahead and use the method that you feel is most productive for you.

#### Browser REPL

Getting the Browser REPL up and running is a little more involved, but
it's still not too bad.

1.  Start a REPL. This will be your Clojure REPL (for the server code).
    Then run:

    ```clojure
    (start-server)
    ```

2.  Start another REPL. This will be your browser REPL (for the client code).
    Then run the following:

    ```clojure
    (require 'weasel.repl.websocket)

    (cemerick.piggieback/cljs-repl
      :repl-env (weasel.repl.websocket/repl-env
                  :ip "0.0.0.0" :port 9001))
    ```

    This REPL should now be switched to ClojureScript.

3.  Open your web app. Either in the browser at http://localhost:7171, or
    through Middlebrow, by running the following in your Clojure REPL (the first
    one we started):

    ```
    (open-window)
    ```

    And you should be all set. Switch to your browser REPL and start issuing
    commands! Or go back to your Clojure REPL when you want to interact
    with your server code.

## License

Copyright © 2015 Solicode

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.