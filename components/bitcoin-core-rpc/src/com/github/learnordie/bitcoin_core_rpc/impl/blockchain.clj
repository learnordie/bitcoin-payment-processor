;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.blockchain
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]
            [com.github.learnordie.logging.interface :as log]))

(defn dump-transaction-output-set
  [rpc-config filename]
  (utils/rpc-call rpc-config "dumptxoutset" [filename]))

(defn get-best-block-hash
  [rpc-config]
  (utils/rpc-call rpc-config "getbestblockhash"))

(defn get-block
  [rpc-config block-hash options]
  (let [params (-> options
                   (assoc :blockhash block-hash))]
    (utils/rpc-call rpc-config "getblock" params)))

(defn get-blockchain-info
  [rpc-config]
  (utils/rpc-call rpc-config "getblockchaininfo"))

(defn get-block-count
  [rpc-config]
  (utils/rpc-call rpc-config "getblockcount"))

(defn get-block-filter
  [rpc-config block-hash options]
  (let [params (-> options
                   (assoc :blockhash block-hash))]
    (utils/rpc-call rpc-config "getblockfilter" params)))

(defn get-block-from-peer
  [rpc-config block-hash peer-id]
  (utils/rpc-call rpc-config "getblockfrompeer" [block-hash peer-id]))

(defn get-block-hash
  [rpc-config height]
  (utils/rpc-call rpc-config "getblockhash" [height]))

(defn get-block-header
  [rpc-config block-hash options]
  (let [params (-> options
                   (assoc :blockhash block-hash))]
    (utils/rpc-call rpc-config "getblockheader" params)))

(defn get-block-stats
  [rpc-config hash-or-height options]
  (let [params (-> options
                   (assoc :hash-or-height hash-or-height))]
    (utils/rpc-call rpc-config "getblockstats" params)))

(defn get-chainstates
  [rpc-config]
  (utils/rpc-call rpc-config "getchainstates"))

(defn get-chain-tips
  [rpc-config]
  (utils/rpc-call rpc-config "getchaintips"))

(defn get-chain-transactions-stats
  [rpc-config options]
  (utils/rpc-call rpc-config "getchaintxstats" options))

(defn get-deployment-info
  [rpc-config options]
  (utils/rpc-call rpc-config "getdeploymentinfo" options))

(defn get-difficulty
  [rpc-config]
  (utils/rpc-call rpc-config "getdifficulty"))

(defn get-mempool-ancestors
  [rpc-config transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call rpc-config "getmempoolancestors" params)))

(defn get-mempool-descendants
  [rpc-config transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call rpc-config "getmempooldescendants" params)))

(defn get-mempool-entry
  [rpc-config transaction-id]
  (utils/rpc-call rpc-config "getmempoolentry" [transaction-id]))

(defn get-mempool-info
  [rpc-config]
  (utils/rpc-call rpc-config "getmempoolinfo"))

(defn get-raw-mempool
  [rpc-config options]
  (utils/rpc-call rpc-config "getrawmempool" options))

(defn get-transaction-output
  [rpc-config transaction-id vout options]
  (let [params (-> options
                   (assoc :txid transaction-id
                          :n vout))]
    (utils/rpc-call rpc-config "gettxout" params)))

(defn get-transaction-output-proof
  [rpc-config transaction-ids options]
  (let [params (-> options
                   (assoc :txids transaction-ids))]
    (utils/rpc-call rpc-config "gettxoutproof" params)))

(defn get-transaction-output-set-info
  [rpc-config options]
  (log/info "This RPC call to `gettxoutsetinfo` may take some time if you are not using `coinstatsindex`")
  (utils/rpc-call rpc-config "gettxoutsetinfo" options))

(defn get-transactions-spending-previous-output
  [rpc-config outputs]
  (utils/rpc-call rpc-config "gettxspendingprevout" [outputs]))

(defn import-mempool
  [rpc-config filepath options]
  (let [params (-> options
                   (assoc :filepath filepath))]
    (utils/rpc-call rpc-config "importmempool" params)))

(defn load-transaction-output-set
  [rpc-config filepath]
  (utils/rpc-call rpc-config "loadtxoutset" [filepath]))

(defn precious-block
  [rpc-config block-hash]
  (utils/rpc-call rpc-config "preciousblock" [block-hash]))

(defn prune-blockchain
  [rpc-config height]
  (utils/rpc-call rpc-config "pruneblockchain" [height]))

(defn save-mempool
  [rpc-config]
  (utils/rpc-call rpc-config "savemempool"))

(defn scan-blocks
  [rpc-config action options]
  (log/info "This RPC call to `scanblocks` may take several minutes.")
  (let [params (-> options
                   (assoc :action action))]
    (utils/rpc-call rpc-config "scanblocks" params)))

(defn scan-transaction-output-set
  [rpc-config action options]
  (let [params (-> options
                   (assoc :action action))]
    (utils/rpc-call rpc-config "scantxoutset" params)))

(defn verify-chain
  [rpc-config options]
  (utils/rpc-call rpc-config "verifychain" options))

(defn verify-transaction-output-proof
  [rpc-config proof]
  (utils/rpc-call rpc-config "verifytxoutproof" [proof]))
