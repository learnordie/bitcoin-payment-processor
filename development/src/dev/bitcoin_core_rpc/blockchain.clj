;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.blockchain
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (btc/dump-transaction-output-set config "/tmp/txoutset.dat")

  (btc/get-best-block-hash config)

  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-block config block-hash))
  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-block config block-hash :verbosity 0))

  (btc/get-blockchain-info config)

  (btc/get-block-count config)

  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-block-filter config block-hash))

  (btc/get-block-hash config 0)

  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-block-header config block-hash))

  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-block-stats config block-hash))
  (btc/get-block-stats config 0)

  (btc/get-chainstates config)

  (btc/get-chain-tips config)

  (btc/get-chain-transactions-stats config)
  (btc/get-chain-transactions-stats config :nblocks 10)
  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-chain-transactions-stats config :blockhash block-hash))

  (btc/get-deployment-info config)
  (let [block-hash (btc/get-best-block-hash config)]
    (btc/get-deployment-info config :blockhash block-hash))

  (btc/get-mempool-info config)

  (let [txid "59293593b12fbd776393b5dd34c23588a38de184baf08a4a9fc674ed03fab485"]
    (btc/get-mempool-ancestors config txid))

  (let [txid "59293593b12fbd776393b5dd34c23588a38de184baf08a4a9fc674ed03fab485"]
    (btc/get-mempool-descendants config txid))

  (let [txid "59293593b12fbd776393b5dd34c23588a38de184baf08a4a9fc674ed03fab485"]
    (btc/get-mempool-entry config txid))

  (btc/get-mempool-info config)

  (btc/get-raw-mempool config)

  (let [txid "59293593b12fbd776393b5dd34c23588a38de184baf08a4a9fc674ed03fab485"]
    (btc/get-transaction-output config txid 0))

  (let [txid "1fef5190a097b2ea757a004dda4949819932528abc6abdf67acc1bdf7c020af5"]
    (btc/get-transaction-output-proof config [txid]))

  (btc/get-transaction-output-set-info config)

  (btc/get-transaction-output-set-info config :hash-or-height 0)

  (let [txid "1fef5190a097b2ea757a004dda4949819932528abc6abdf67acc1bdf7c020af5"]
    (btc/get-transactions-spending-previous-output config [{:txid txid :vout 3}]))

  (btc/import-mempool config "/tmp/mempool.dat")

  (btc/load-transaction-output-set config "/tmp/txoutset.dat")

  (let [block-hash (btc/get-best-block-hash config)]
    (btc/precious-block config block-hash))

  (btc/prune-blockchain config 1000)

  (btc/save-mempool config)

  (btc/scan-blocks config "start")

  (btc/scan-transaction-output-set config "start")

  (btc/verify-chain config)

  (let [txid "1fef5190a097b2ea757a004dda4949819932528abc6abdf67acc1bdf7c020af5"
        proof (btc/get-transaction-output-proof config [txid])]
    (btc/verify-transaction-output-proof config proof))

  )


