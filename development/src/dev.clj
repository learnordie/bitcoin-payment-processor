;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (btc/get-blockchain-info config)

  )
