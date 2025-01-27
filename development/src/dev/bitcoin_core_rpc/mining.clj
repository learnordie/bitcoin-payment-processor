;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.mining
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (btc/get-block-template config {:rules ["segwit"]})

  (btc/get-mining-info config)

  (btc/get-network-hashes-per-second config)
  (btc/get-network-hashes-per-second config :height 100)

  (let [txid "59293593b12fbd776393b5dd34c23588a38de184baf08a4a9fc674ed03fab485"]
    (btc/prioritise-transaction config txid 1))

  (btc/get-prioritised-transactions config)

  (btc/submit-block config "hex-encoded-block-data-to-submit")

  (btc/submit-header config "hex-encoded-block-header-to-submit")

  )

