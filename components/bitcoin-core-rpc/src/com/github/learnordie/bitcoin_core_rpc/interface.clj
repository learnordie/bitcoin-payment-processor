;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.interface
  {:clj-kondo/config '{:linters {:unused-binding {:exclude-destructured-keys-in-fn-args true}}}}
  (:require [com.github.learnordie.bitcoin-core-rpc.impl.blockchain :as blockchain]))

;;; Blockchain functions

(defn dump-transaction-output-set
  "Writes the serialized UTXO set to a file."
  [rpc-config filename]
  (blockchain/dump-transaction-output-set rpc-config filename))

(defn get-best-block-hash
  "Returns the hash of the best (tip) block in the most-work fully-validated chain."
  [rpc-config]
  (blockchain/get-best-block-hash rpc-config))

(defn get-block
  "Returns information about the block with the given block hash."
  [rpc-config block-hash & {:keys [verbosity] :as options}]
  (blockchain/get-block rpc-config block-hash options))

(defn get-blockchain-info
  "Returns an object containing various state info regarding blockchain processing."
  [rpc-config]
  (blockchain/get-blockchain-info rpc-config))

(defn get-block-count
  "Returns the height of the most-work fully-validated chain."
  [rpc-config]
  (blockchain/get-block-count rpc-config))

(defn get-block-filter
  "Retrieves a BIP 157 content filter for a particular block."
  [rpc-config block-hash & {:keys [filtertype] :as options}]
  (blockchain/get-block-filter rpc-config block-hash options))

(defn get-block-from-peer
  "Attempts to fetch block from a given peer."
  [rpc-config block-hash peer-id]
  (blockchain/get-block-from-peer rpc-config block-hash peer-id))

(defn get-block-hash
  "Returns hash of block in best-block-chain at height provided."
  [rpc-config height]
  (blockchain/get-block-hash rpc-config height))

(defn get-block-header
  "Returns information about blockheader `block-hash`."
  [rpc-config block-hash & {:keys [verbose] :as options}]
  (blockchain/get-block-header rpc-config block-hash options))

(defn get-block-stats
  "Computes per block statistics for a given window. All amounts are in satoshis."
  [rpc-config hash-or-height & {:keys [stats] :as options}]
  (blockchain/get-block-stats rpc-config hash-or-height options))

(defn get-chainstates
  "Returns information about chainstates."
  [rpc-config]
  (blockchain/get-chainstates rpc-config))

(defn get-chain-tips
  "Returns information about all known tips in the block tree, including the main chain as well as orphaned branches."
  [rpc-config]
  (blockchain/get-chain-tips rpc-config))

(defn get-chain-transactions-stats
  "Computes statistics about the total number and rate of transactions in the chain."
  [rpc-config & {:keys [blockhash nblocks] :as options}]
  (blockchain/get-chain-transactions-stats rpc-config options))

(defn get-deployment-info
  "Returns an object containing various state info regarding deployments of consensus changes."
  [rpc-config & {:keys [blockhash] :as options}]
  (blockchain/get-deployment-info rpc-config options))

(defn get-difficulty
  "Returns the proof-of-work difficulty as a multiple of the minimum difficulty."
  [rpc-config]
  (blockchain/get-difficulty rpc-config))

(defn get-mempool-ancestors
  "Returns all in-mempool ancestors for a transaction in the mempool."
  [rpc-config transaction-id & {:keys [verbose] :as options}]
  (blockchain/get-mempool-ancestors rpc-config transaction-id options))

(defn get-mempool-descendants
  "Returns all in-mempool descendants for a transaction in the mempool."
  [rpc-config transaction-id & {:keys [verbose] :as options}]
  (blockchain/get-mempool-descendants rpc-config transaction-id options))

(defn get-mempool-entry
  "Returns mempool data for given transaction."
  [rpc-config transaction-id]
  (blockchain/get-mempool-entry rpc-config transaction-id))

(defn get-mempool-info
  "Returns details on the active state of the TX memory pool."
  [rpc-config]
  (blockchain/get-mempool-info rpc-config))

(defn get-raw-mempool
  "Returns all transaction ids in memory pool as a JSON array of string transaction ids."
  [rpc-config & {:keys [mempool-sequence verbose] :as options}]
  (blockchain/get-raw-mempool rpc-config options))

(defn get-transaction-output
  "Returns details about an unspent transaction output."
  [rpc-config transaction-id vout & {:keys [include-mempool] :as options}]
  (blockchain/get-transaction-output rpc-config transaction-id vout options))

(defn get-transaction-output-proof
  "Returns a hex-encoded proof that `transaction-id` was included in a block."
  [rpc-config transaction-ids & {:keys [blockhash] :as options}]
  (blockchain/get-transaction-output-proof rpc-config transaction-ids options))

(defn get-transaction-output-set-info
  "Returns statistics about the unspent transaction output set."
  [rpc-config & {:keys [hash-or-height hash-type use-index] :as options}]
  (blockchain/get-transaction-output-set-info rpc-config options))

(defn get-transactions-spending-previous-output
  "Scans the mempool to find transactions spending any of the given `outputs`."
  [rpc-config outputs]
  (blockchain/get-transactions-spending-previous-output rpc-config outputs))

(defn import-mempool
  "Imports the mempool from the given file and attempts to add its contents to the mempool."
  [rpc-config filepath & {:keys [apply-fee-delta-priority apply-unbroadcast-set use-current-time] :as options}]
  (blockchain/import-mempool rpc-config filepath options))

(defn load-transaction-output-set
  "Loads the serialized UTXO set from a file."
  [rpc-config filepath]
  (blockchain/load-transaction-output-set rpc-config filepath))

(defn precious-block
  "Treats a block as if it were received before others with the same work."
  [rpc-config block-hash]
  (blockchain/precious-block rpc-config block-hash))

(defn prune-blockchain
  "Prunes the blockchain up to a specified height."
  [rpc-config height]
  (blockchain/prune-blockchain rpc-config height))

(defn save-mempool
  "Dumps the mempool to disk."
  [rpc-config]
  (blockchain/save-mempool rpc-config))

(defn scan-blocks
  "Returns relevant blockhashes for the given descriptors (requires `blockfilterindex`)."
  [rpc-config action & {:keys [filtertype options scanobjects start-height stop-height] :as all-options}]
  (blockchain/scan-blocks rpc-config action all-options))

(defn scan-transaction-output-set
  "Scans the unspent transaction output set for entries that match certain output descriptors."
  [rpc-config action & {:keys [scanobjects] :as options}]
  (blockchain/scan-blocks rpc-config action options))

(defn verify-chain
  "Verifies blockchain database."
  [rpc-config & {:keys [checklevel checkdepth] :as options}]
  (blockchain/verify-chain rpc-config options))

(defn verify-transaction-output-proof
  "Verifies that a proof points to a transaction in a block."
  [rpc-config proof]
  (blockchain/verify-transaction-output-proof rpc-config proof))
