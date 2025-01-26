;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.github.learnordie.config.interface :as config]
            [com.github.learnordie.logging.interface :as log]))

(comment

  (config/read-config "dev/config.edn")
  (config/read-config "dev/config.edn" :dev)
  (config/read-config "dev/config.edn" :prod)

  (log/info "This is an info message.")

  )
