;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.network
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (let [node-address "192.168.0.6:8333"]
    (btc/add-node config node-address "onetry" :v2transport true))

  (btc/clear-banned config)

  (let [node-address "192.168.0.6:8333"]
    (btc/disconnect-node config :address node-address))

  (let [node-address "192.168.0.6:8333"]
    (btc/get-added-node-info config :node node-address))

  (btc/get-address-manager-info config)

  (btc/get-connection-count config)

  (btc/get-network-totals config)

  (btc/get-network-info config)

  (btc/get-node-addresses config)

  (btc/get-peer-info config)

  (btc/list-banned config)

  (btc/ping config)

  (let [subnet "192.168.0.6"
        bantime 86400]
    (btc/set-ban config subnet "add" :bantime bantime))

  (btc/set-network-active config true)

  )
