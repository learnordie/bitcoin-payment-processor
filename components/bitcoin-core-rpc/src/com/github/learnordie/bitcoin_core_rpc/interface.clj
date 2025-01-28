;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.interface
  (:require [com.github.learnordie.bitcoin-core-rpc.impl.blockchain :as blockchain]
            [com.github.learnordie.bitcoin-core-rpc.impl.control :as control]
            [com.github.learnordie.bitcoin-core-rpc.impl.mining :as mining]
            [com.github.learnordie.bitcoin-core-rpc.impl.network :as network]
            [com.github.learnordie.bitcoin-core-rpc.impl.raw-transactions :as raw-transactions]
            [com.github.learnordie.bitcoin-core-rpc.impl.signer :as signer]
            [com.github.learnordie.bitcoin-core-rpc.impl.util :as util]
            [com.github.learnordie.bitcoin-core-rpc.impl.wallet :as wallet]))

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

;;; Util functions

(defn create-multisig
  "Creates a multi-signature address with `n` signature of `m` keys required."
  [rpc-config nrequired keys & {:keys [address-type] :as options}]
  (util/create-multisig rpc-config nrequired keys options))

(defn derive-addresses
  "Derives one or more addresses correspondingto an output descriptor."
  [rpc-config descriptor & {:keys [range] :as options}]
  (util/derive-addresses rpc-config descriptor options))

(defn estimate-smart-fee
  "Estimates the approximate fee per kilobyte needed for a transaction to begin confirmation within `conf-target` blocks if possible and returns the number of blocks for which the estimate is valid."
  [rpc-config conf-target & {:keys [estimate-mode] :as options}]
  (util/estimate-smart-fee rpc-config conf-target options))

(defn get-descriptor-info
  "Analyzes a descriptor."
  [rpc-config descriptor]
  (util/get-descriptor-info rpc-config descriptor))

(defn get-index-info
  "Returns the status of one or all available indeces currently running in the node."
  [rpc-config & {:keys [index-name] :as options}]
  (util/get-index-info rpc-config options))

(defn sign-message-with-private-key
  "Signs a message with the private key of an address."
  [rpc-config private-key message]
  (util/sign-message-with-private-key rpc-config private-key message))

(defn validate-address
  "Returns information about the given bitcoin address."
  [rpc-config address]
  (util/validate-address rpc-config address))

(defn verify-message
  "Verifies a signed message."
  [rpc-config address signature message]
  (util/verify-message rpc-config address signature message))

;;; Wallet functions

(defn abandon-transaction
  "Marks in-wallet transaction `transaction-id` as abandoned."
  [rpc-config wallet-name transaction-id]
  (wallet/abandon-transaction rpc-config wallet-name transaction-id))

(defn abort-rescan
  "Stops current wallet rescan triggered by an RPC call."
  [rpc-config wallet-name]
  (wallet/abort-rescan rpc-config wallet-name))

(defn add-multisig-address
  "Adds a nrequired-to-sign multisignature address to the wallet."
  [rpc-config wallet-name nrequired keys & {:keys [address-type label] :as options}]
  (wallet/add-multisig-address rpc-config wallet-name nrequired keys options))

(defn backup-wallet
  "Safely copies the current wallet file to the specified destination, which can either be a directory or a path with a filename."
  [rpc-config wallet-name destination]
  (wallet/backup-wallet rpc-config wallet-name destination))

(defn bump-fee
  "Bumps the fee of an opt-in-RBF transaction `transaction-id`, replacing it with a new transaction."
  [rpc-config wallet-name transaction-id & {:keys [estimate-mode fee-rate original-change-index outputs replaceable] :as options}]
  (wallet/bump-fee rpc-config wallet-name transaction-id options))

(defn create-wallet
  "Creates and loads a new wallet."
  [rpc-config wallet-name & {:keys [avoid-reuse blank descriptors disable-private-keys external-signer load-on-startup passphrase]
                             :as options}]
  (wallet/create-wallet rpc-config wallet-name options))

(defn create-wallet-descriptor
  "Creates the wallet's descriptor for the given address type."
  [rpc-config wallet-name address-type & {:keys [hdkey internal] :as options}]
  (wallet/create-wallet-descriptor rpc-config wallet-name address-type options))

(defn dump-private-key
  "Reveals the private key corresponding to `address`."
  [rpc-config wallet-name address]
  (wallet/dump-private-key rpc-config wallet-name address))

(defn dump-wallet
  "Dumps all wallet keys in a human-readable format to a server-side file."
  [rpc-config wallet-name filename]
  (wallet/dump-wallet rpc-config wallet-name filename))

(defn encrypt-wallet
  "Encrypts the wallet with `passphrase`."
  [rpc-config wallet-name passphrase]
  (wallet/encrypt-wallet rpc-config wallet-name passphrase))

(defn get-addresses-by-label
  "Returns the list of addresses assigned the specified label."
  [rpc-config wallet-name label]
  (wallet/get-addresses-by-label rpc-config wallet-name label))

(defn get-address-info
  "Returns information about the given bitcoin address."
  [rpc-config wallet-name address]
  (wallet/get-address-info rpc-config wallet-name address))

(defn get-balance
  "Returns the total available balance."
  [rpc-config wallet-name & {:keys [avoid-reuse include-watchonly minconf]
                             :as options}]
  (wallet/get-balance rpc-config wallet-name options))

(defn get-balances
  "Returns an object with all balances in BTC."
  [rpc-config wallet-name & {:keys [avoid-reuse include-watchonly minconf]
                             :as options}]
  (wallet/get-balances rpc-config wallet-name options))

(defn get-hd-keys
  "Lists all BIP 32 HD keys in the wallet and which descriptors use them."
  [rpc-config wallet-name & {:keys [active-only private] :as options}]
  (wallet/get-hd-keys rpc-config wallet-name options))

(defn get-new-address
  "Returns a new Bitcoin address for receiving payments."
  [rpc-config wallet-name & {:keys [address-type label] :as options}]
  (wallet/get-new-address rpc-config wallet-name options))

(defn get-raw-change-address
  "Returns a new Bitcoin address for receiving change.
  This is for use with raw transactions, NOT normal use."
  [rpc-config wallet-name & {:keys [address-type] :as options}]
  (wallet/get-raw-change-address rpc-config wallet-name options))

(defn get-received-by-address
  "Returns the total amount received by the given address in transactions with at least `minconf` confirmations."
  [rpc-config wallet-name address & {:keys [include-immature-coinbase minconf] :as options}]
  (wallet/get-received-by-address rpc-config wallet-name address options))

(defn get-received-by-label
  "Returns the total amount received by addresses with `label` in transactions with at least `minconf` confirmations."
  [rpc-config wallet-name label & {:keys [include-immature-coinbase minconf] :as options}]
  (wallet/get-received-by-label rpc-config wallet-name label options))

(defn get-transaction
  "Gets detailed information about in-wallet transaction `transaction-id`."
  [rpc-config wallet-name transaction-id & {:keys [include-watchonly verbose] :as options}]
  (wallet/get-transaction rpc-config wallet-name transaction-id options))

(defn get-wallet-info
  "Returns an object containing various wallet state info."
  [rpc-config wallet-name]
  (wallet/get-wallet-info rpc-config wallet-name))

(defn import-address
  "Adds an address or script (in hex) that can be watched as if it were in your wallet but cannot be used to spend. Requires a new wallet backup."
  [rpc-config wallet-name address & {:keys [label p2sh rescan] :as options}]
  (wallet/import-address rpc-config wallet-name address options))

(defn import-descriptors
  "Imports descriptors. Requires a new wallet backup."
  [rpc-config wallet-name requests]
  (wallet/import-descriptors rpc-config wallet-name requests))

(defn import-multi
  "Imports addresses/scripts (with private or public keys, redeem script (P2SH)), optionally rescanning the blockchain from the earliest creation time of the imported scripts. Requires a new wallet backup."
  [rpc-config wallet-name requests & {:keys [rescan] :as options}]
  (wallet/import-multi rpc-config wallet-name requests options))

(defn import-private-key
  "Adds a private key (as returned by `dump-private-key`) to your wallet. Requires a new wallet backup."
  [rpc-config wallet-name private-key & {:keys [label rescan] :as options}]
  (wallet/import-private-key rpc-config wallet-name private-key options))

(defn import-pruned-funds
  "Imports funds without rescan."
  [rpc-config wallet-name raw-transaction transaction-out-proof]
  (wallet/import-pruned-funds rpc-config wallet-name raw-transaction transaction-out-proof))

(defn import-public-key
  "Adds a public key (in hex) that can be watched as if it were in your wallet but cannot be used to spend. Requires a new wallet backup."
  [rpc-config wallet-name public-key & {:keys [label rescan] :as options}]
  (wallet/import-public-key rpc-config wallet-name public-key options))

(defn import-wallet
  "Imports keys from a wallet dump file (see `dump-wallet`). Requires a new wallet backup to include imported keys."
  [rpc-config wallet-name filename]
  (wallet/import-wallet rpc-config wallet-name filename))

(defn keypool-refill
  "Fills the keypool. Requires wallet passphrase to be set with `wallet-passphrase` call if wallet is encrypted."
  [rpc-config wallet-name & {:keys [newsize] :as options}]
  (wallet/keypool-refill rpc-config wallet-name options))

(defn list-address-groupings
  "Lists groups of addresses which have had their common ownership made public by common use as inputs or as the resulting change in past transactions."
  [rpc-config wallet-name]
  (wallet/list-address-groupings rpc-config wallet-name))

(defn list-descriptors
  "Lists descriptors imported into a descriptor-enabled wallet."
  [rpc-config wallet-name & {:keys [private] :as options}]
  (wallet/list-descriptors rpc-config wallet-name options))

(defn list-labels
  "Returns the list of all labels, or labels that are assigned to addresses with a specific purpose."
  [rpc-config wallet-name & {:keys [purpose] :as options}]
  (wallet/list-labels rpc-config wallet-name options))

(defn list-lock-unspent
  "Returns a list of temporarily unspendable outputs."
  [rpc-config wallet-name]
  (wallet/list-lock-unspent rpc-config wallet-name))

(defn list-received-by-address
  "Lists balances by receiving address."
  [rpc-config wallet-name & {:keys [address-filter include-empty include-immature-coinbase include-watchonly minconf] :as options}]
  (wallet/list-received-by-address rpc-config wallet-name options))

(defn list-received-by-label
  "Lists received transactions by label."
  [rpc-config wallet-name & {:keys [include-empty include-immature-coinbase include-watchonly minconf] :as options}]
  (wallet/list-received-by-label rpc-config wallet-name options))

(defn list-since-block
  "Gets all transactions in blocks since block `blockhash`, or all transactions if omitted."
  [rpc-config wallet-name & {:keys [blockhash include-change include-removed include-watchonly label target-confirmations] :as options}]
  (wallet/list-since-block rpc-config wallet-name options))

(defn list-transactions
  "Returns up to `count` most recent transactions skipping the first `from` transactions. If `label` is provided, only incoming transactions paying to addresses with the specified label are returned."
  [rpc-config wallet-name & {:keys [account count from include-watchonly label] :as options}]
  (wallet/list-transactions rpc-config wallet-name options))

(defn list-unspent
  "Returns array of unspent transaction outputs with between `minconf` and `maxconf` (inclusive) confirmations."
  [rpc-config wallet-name & {:keys [addresses include-unsafe minconf maxconf query-options] :as options}]
  (wallet/list-unspent rpc-config wallet-name options))

(defn list-wallet-dir
  "Returns a list of wallets in the wallet directory."
  [rpc-config]
  (wallet/list-wallet-dir rpc-config))

(defn list-wallets
  "Returns a list of currently loaded wallets."
  [rpc-config]
  (wallet/list-wallets rpc-config))

(defn load-wallet
  "Loads a wallet from a wallet file or directory."
  [rpc-config filename & {:keys [load-on-startup] :as options}]
  (wallet/load-wallet rpc-config filename options))

(defn lock-unspent
  "Updates list of temporarily unspendable outputs."
  [rpc-config wallet-name unlock & {:keys [persistent transactions] :as options}]
  (wallet/lock-unspent rpc-config wallet-name unlock options))

(defn migrate-wallet
  "Migrates the wallet to a descriptor wallet. A new wallet backup will need to be made."
  [rpc-config wallet-name &{:keys [passphrase] :as options}]
  (wallet/migrate-wallet rpc-config wallet-name options))

(defn new-keypool
  "Entirely clears and refills the keypool.

  WARNING: On non-HD wallets, this will require a new backup immediately, to include the new keys.
  Requires wallet passphrase to be set with `wallet-passphrase` call if wallet is encrypted."
  [rpc-config wallet-name]
  (wallet/new-keypool rpc-config wallet-name))

(defn psbt-bump-fee
  "Bumps the fee of a PSBT transaction `transaction-id`, replacing it with a new PSBT transaction."
  [rpc-config wallet-name transaction-id & {:keys [conf-target estimate-mode fee-rate original-change-index outputs replaceable] :as options}]
  (wallet/psbt-bump-fee rpc-config wallet-name transaction-id options))

(defn remove-pruned-funds
  "Deletes the specified transaction from the wallet. Meant for use with pruned wallets and as a companion to `import-pruned-funds`."
  [rpc-config wallet-name transaction-id]
  (wallet/remove-pruned-funds rpc-config wallet-name transaction-id))

(defn rescan-blockchain
  "Rescans the local blockchain for wallet related transactions."
  [rpc-config wallet-name & {:keys [start-height stop-height] :as options}]
  (wallet/rescan-blockchain rpc-config wallet-name options))

(defn restore-wallet
  "Restores and loadsa wallet from backup."
  [rpc-config wallet-name backup-file & {:keys [load-on-startup] :as options}]
  (wallet/restore-wallet rpc-config wallet-name backup-file options))

(defn send
  "Sends a transaction."
  [rpc-config wallet-name outputs & {:keys [conf-target estimate-mode fee-rate options] :as all-options}]
  (wallet/send rpc-config wallet-name outputs all-options))

(defn send-all
  "Spends the value of all (or specific) confirmed UTXOs and unconfirmed change in the wallet to one or more recipients."
  [rpc-config wallet-name recipients & {:keys [conf-target estimate-mode fee-rate options] :as all-options}]
  (wallet/send-all rpc-config wallet-name recipients all-options))

(defn send-many
  "Sends multiple times."
  [rpc-config wallet-name amounts & {:keys [comment conf-taget estimate-mode fee-rate minconf replaceable subtractfeefrom verbose] :as options}]
  (wallet/send-many rpc-config wallet-name amounts options))

(defn send-to-address
  "Sends an amount to a given address."
  [rpc-config wallet-name amount address & {:keys [avoid-reuse comment comment-to conf-target estimate-mode fee-rate replaceable subtractfeefromamount verbose] :as options}]
  (wallet/send-to-address rpc-config wallet-name amount address options))

(defn set-hd-seed
  "Sets or generates a new HD wallet seed.

  Note that you will need to MAKE A NEW BACKUP of your wallet after setting the HD wallet seed.
  Requires wallet passphrase to be set with walletpassphrase call if wallet is encrypted.
  Note: This command is only compatible with legacy wallets."
  [rpc-config wallet-name & {:keys [newkeypool seed] :as options}]
  (wallet/set-hd-seed rpc-config wallet-name options))

(defn set-label
  "Sets the label associated with the given address."
  [rpc-config wallet-name address label]
  (wallet/set-label rpc-config wallet-name address label))

(defn set-transaction-fee
  "Sets the transaction fee rate in BTC/kvB for this wallet."
  [rpc-config wallet-name amount]
  (wallet/set-transaction-fee rpc-config wallet-name amount))

(defn set-wallet-flag
  "Changes the state of the given walletflag for a wallet."
  [rpc-config wallet-name flag & {:keys [value] :as options}]
  (wallet/set-wallet-flag rpc-config wallet-name flag options))

(defn sign-message
  "Signs a message with the private key of an address."
  [rpc-config wallet-name address message]
  (wallet/sign-message rpc-config wallet-name address message))

(defn sign-raw-transaction-with-wallet
  "Signs inputs for raw transaction (serialized, hex-encoded)."
  [rpc-config wallet-name hex-string & {:keys [prevtxs sighashtype] :as options}]
  (wallet/sign-raw-transaction-with-wallet rpc-config wallet-name hex-string options))

(defn simulate-raw-transaction
  "Calculates the balance change resulting in the signing and broadcasting of the given transaction(s)."
  [rpc-config wallet-name & {:keys [rawtxs options] :as all-options}]
  (wallet/simulate-raw-transaction rpc-config wallet-name all-options))

(defn unload-wallet
  "Unloads the wallet specified by `wallet-name`."
  [rpc-config wallet-name & {:keys [load-on-startup] :as options}]
  (wallet/unload-wallet rpc-config wallet-name options))

(defn upgrade-wallet
  "Upgrades the wallet. Upgrades to the latest version if no version number is specified."
  [rpc-config wallet-name & {:keys [version] :as options}]
  (wallet/upgrade-wallet rpc-config wallet-name options))

(defn wallet-create-funded-psbt
  "Creates an funds a transaction in the Partially Signed Bitcoin Transaction format."
  [rpc-config wallet-name outputs & {:keys [inputs locktime options bip32derivs] :as all-options}]
  (wallet/wallet-create-funded-psbt rpc-config wallet-name outputs all-options))

(defn wallet-display-address
  "Dispalys address on an external signer for verification."
  [rpc-config wallet-name address]
  (wallet/wallet-display-address rpc-config wallet-name address))

(defn wallet-lock
  "Removes the wallet encryption key from memory, locking the wallet."
  [rpc-config wallet-name]
  (wallet/wallet-lock rpc-config wallet-name))

(defn wallet-passphrase
  "Stores the wallet decryption key in memory for `timeout` seconds."
  [rpc-config wallet-name passphrase timeout]
  (wallet/wallet-passphrase rpc-config wallet-name passphrase timeout))

(defn wallet-passphrase-change
  "Changes the wallet passphrase from `old-passphrase` to `new-passphrase`."
  [rpc-config wallet-name old-passphrase new-passphrase]
  (wallet/wallet-passphrase-change rpc-config wallet-name old-passphrase new-passphrase))

(defn wallet-process-psbt
  "Updates a PSBT with input information from our wallet and then signs inputs that we can sign for."
  [rpc-config wallet-name psbt & {:keys [bip32derivs finalize sighashtype sign] :as options}]
  (wallet/wallet-process-psbt rpc-config wallet-name psbt options))
