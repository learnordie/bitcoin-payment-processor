;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.interface
  (:require [com.github.learnordie.bitcoin-core-rpc.impl.blockchain :as blockchain]
            [com.github.learnordie.bitcoin-core-rpc.impl.control :as control]
            [com.github.learnordie.bitcoin-core-rpc.impl.mining :as mining]
            [com.github.learnordie.bitcoin-core-rpc.impl.network :as network]
            [com.github.learnordie.bitcoin-core-rpc.impl.raw-transactions :as raw-transactions]
            [com.github.learnordie.bitcoin-core-rpc.impl.signer :as signer]))

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

;;; Control functions

(defn get-memory-info
  "Returns an object containing information about memory usage."
  [rpc-config & {:keys [mode] :as options}]
  (control/get-memory-info rpc-config options))

(defn get-rpc-info
  "Returns details of the RPC server."
  [rpc-config]
  (control/get-rpc-info rpc-config))

(defn help
  "Lists all commands, or get help for a specified command."
  [rpc-config & {:keys [command] :as options}]
  (control/help rpc-config options))

(defn logging
  "Gets and sets the logging configuration."
  [rpc-config & {:keys [exclude include] :as options}]
  (control/logging rpc-config options))

(defn stop
  "Requests a graceful shutdown of Bitcoin Core."
  [rpc-config]
  (control/stop rpc-config))

(defn uptime
  "Returns the total uptime of the server."
  [rpc-config]
  (control/uptime rpc-config))

;;; Mining functions

(defn get-block-template
  "Returns data needed to construct a block to work on."
  [rpc-config template-request]
  (mining/get-block-template rpc-config template-request))

(defn get-mining-info
  "Returns an object containing mining-related information."
  [rpc-config]
  (mining/get-mining-info rpc-config))

(defn get-network-hashes-per-second
  "Returns the estimated network hashes per second based on the last `nblocks` blocks."
  [rpc-config & {:keys [height nblocks] :as options}]
  (mining/get-network-hashes-per-second rpc-config options))

(defn get-prioritised-transactions
  "Returns a map of all user-created (see `prioritise-transaction`) fee deltas by transaction id, and whether the transaction is present in the mempool."
  [rpc-config]
  (mining/get-prioritised-transactions rpc-config))

(defn prioritise-transaction
  "Accepts the transaction into mined blocks at a higher (or lower) priority."
  [rpc-config transaction-id fee-delta]
  (mining/prioritise-transaction rpc-config transaction-id fee-delta))

(defn submit-block
  "Attempts to submit new block to network."
  [rpc-config hex-data & _dummy]
  (mining/submit-block rpc-config hex-data))

(defn submit-header
  "Decodes the given hexdata as a header and submits it as a candidate chaintip if valid."
  [rpc-config hex-data]
  (mining/submit-header rpc-config hex-data))

;;; Network functions

(defn add-node
  "Attempts to add or remove a node form the addnode list or try a connection to a node once."
  [rpc-config node command & {:keys [v2transport] :as options}]
  (network/add-node rpc-config node command options))

(defn clear-banned
  "Clears all banned IPs."
  [rpc-config]
  (network/clear-banned rpc-config))

(defn disconnect-node
  "Immediately disconnects from the specified peer node."
  [rpc-config & {:keys [address nodeid] :as options}]
  (network/disconnect-node rpc-config options))

(defn get-added-node-info
  "Returns information about the given added node, or all added nodes (except onetry addnodes are not listed here)."
  [rpc-config & {:keys [node] :as options}]
  (network/get-added-node-info rpc-config options))

(defn get-address-manager-info
  "Provides information about the node's address manager by returning the number of addresses in the `new` and `tried` tables and their sum for all networks."
  [rpc-config]
  (network/get-address-manager-info rpc-config))

(defn get-connection-count
  "Returns the number of connections to other nodes."
  [rpc-config]
  (network/get-connection-count rpc-config))

(defn get-network-totals
  "Returns information about network traffic, including bytes in, bytes out, and current system time."
  [rpc-config]
  (network/get-network-totals rpc-config))

(defn get-network-info
  "Returns an object containing various state info regarding P2P networking."
  [rpc-config]
  (network/get-network-info rpc-config))

(defn get-node-addresses
  "Returns known addresses, after filtering for quality and recency."
  [rpc-config & {:keys [count network] :as options}]
  (network/get-node-addresses rpc-config options))

(defn get-peer-info
  "Returns data about each connected network peer as a JSON array of objects."
  [rpc-config]
  (network/get-peer-info rpc-config))

(defn list-banned
  "Lists all manually banned IPs/subnets."
  [rpc-config]
  (network/list-banned rpc-config))

(defn ping
  "Requests that a ping be sent to all other nodes, to measure ping time."
  [rpc-config]
  (network/ping rpc-config))

(defn set-ban
  "Attempts to add or remove an IP/subnet from the banned list."
  [rpc-config subnet command & {:keys [absolute bantime] :as options}]
  (network/set-ban rpc-config subnet command options))

(defn set-network-active
  "Disables/enables all p2p network activity."
  [rpc-config state]
  (network/set-network-active rpc-config state))

;;; Raw transactions functions

(defn analyze-psbt
  "Analyzes and provides information about the current status of a PSBT and its inputs."
  [rpc-config psbt]
  (raw-transactions/analyze-psbt rpc-config psbt))

(defn combine-psbt
  "Combines multiple partially signed Bitcoin transactions into one transaction."
  [rpc-config transactions]
  (raw-transactions/combine-psbt rpc-config transactions))

(defn combine-raw-transaction
  "Combines multiple partially signed transactions into one transaction."
  [rpc-config transactions]
  (raw-transactions/combine-raw-transaction rpc-config transactions))

(defn convert-to-psbt
  "Converts a network serialized transaction to a PSBT."
  [rpc-config hex-string & {:keys [iswitness permitsigdata] :as options}]
  (raw-transactions/convert-to-psbt rpc-config hex-string options))

(defn create-psbt
  "Creates a transaction in the Partially Signed Transaction format."
  [rpc-config inputs outputs & {:keys [locktime replaceable] :as options}]
  (raw-transactions/create-psbt rpc-config inputs outputs options))

(defn create-raw-transaction
  "Creates a transaction spending the given inputs and creating new outputs."
  [rpc-config inputs outputs & {:keys [locktime replaceable] :as options}]
  (raw-transactions/create-raw-transaction rpc-config inputs outputs options))

(defn decode-psbt
  "Returns a JSON object representing the serialized, base64-encoded partially signed Bitcoin transaction."
  [rpc-config psbt]
  (raw-transactions/decode-psbt rpc-config psbt))

(defn decode-raw-transaction
  "Returns a JSON object representing the serialized, hex-encoded transaction."
  [rpc-config hex-string & {:keys [iswitness] :as options}]
  (raw-transactions/decode-raw-transaction rpc-config hex-string options))

(defn decode-script
  "Decodes a hex-encoded script."
  [rpc-config hex-string]
  (raw-transactions/decode-script rpc-config hex-string))

(defn descriptor-process-psbt
  "Updates all segwit inputs in a PSBT with information from output descriptors, the UTXO set, or the mempool."
  [rpc-config psbt descriptors & {:keys [bip32derivs finalize sighashtype] :as options}]
  (raw-transactions/descriptor-process-psbt rpc-config psbt descriptors options))

(defn finalize-psbt
  "Finalizes the inputs of a PSBT."
  [rpc-config psbt & {:keys [extract] :as options}]
  (raw-transactions/finalize-psbt rpc-config psbt options))

(defn fund-raw-transaction
  "Adds inputs to a transaction until it has enough in value to meet its out value."
  [rpc-config hex-string & {:keys [iswitness options] :as all-options}]
  (raw-transactions/fund-raw-transaction rpc-config hex-string all-options))

(defn get-raw-transaction
  "Returns a JSON object representing the serialized, hex-encoded transaction."
  [rpc-config transaction-id & {:keys [blockhash verbosity] :as options}]
  (raw-transactions/get-raw-transaction rpc-config transaction-id options))

(defn join-psbts
  "Joins multiple distinct PSBTs with different inputs and outputs into one PSBT with inputs and outputs from all of the PSBTs."
  [rpc-config transactions]
  (raw-transactions/join-psbts rpc-config transactions))

(defn send-raw-transaction
  "Submits raw transaction (serialized, hex-encoded) to local node and network."
  [rpc-config hex-string & {:keys [maxburnamount maxfeerate] :as options}]
  (raw-transactions/send-raw-transaction rpc-config hex-string options))

(defn sign-raw-transaction-with-key
  "Signs inputs for raw transaction (serialized, hex-encoded)."
  [rpc-config hex-string private-keys & {:keys [prevtxs sighashtype] :as options}]
  (raw-transactions/sign-raw-transaction-with-key rpc-config hex-string private-keys options))

(defn submit-package
  "Submits a package of raw transactions (serialized, hex-encoded) to local node."
  [rpc-config package & {:keys [maxburnamount maxfeerate] :as options}]
  (raw-transactions/submit-package rpc-config package options))

(defn test-mempool-accept
  "Returns result of mempool acceptance tests indicating if raw transaction(s) (serialized, hex-encoded) would be accepted by mempool."
  [rpc-config transactions & {:keys [maxfeerate] :as options}]
  (raw-transactions/test-mempool-accept rpc-config transactions options))

(defn utxo-update-psbt
  "Updates all segwit inputs and outputs in a PSBT with data from output descriptors, the UTXO set, txindex, or the mempool."
  [rpc-config psbt & {:keys [descriptors] :as options}]
  (raw-transactions/utxo-update-psbt rpc-config psbt options))

;;; Signer functions

(defn enumerate-signers
  "Returns a list of external signers from `-signer`."
  [rpc-config]
  (signer/enumerate-signers rpc-config))
