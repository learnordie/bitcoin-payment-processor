;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.control
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (btc/get-memory-info config)
  (btc/get-memory-info config :mode "mallocinfo")
        
  (btc/get-rpc-info config)
        
  (btc/help config)
  (btc/help config :command "getblockchaininfo")
        
  (btc/logging config)
  (btc/logging config :include ["estimatefee"] :exclude ["rpc" "zmq"])
        
  (btc/stop config)
        
  (btc/uptime config)

  )

