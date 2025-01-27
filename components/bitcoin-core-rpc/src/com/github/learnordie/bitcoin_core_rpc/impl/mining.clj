;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.mining
  (:require[com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn get-block-template
  [rpc-config template-request]
  (utils/rpc-call rpc-config "getblocktemplate" [template-request]))

(defn get-mining-info
  [rpc-config]
  (utils/rpc-call rpc-config "getmininginfo"))

(defn get-network-hashes-per-second
  [rpc-config options]
  (utils/rpc-call rpc-config "getnetworkhashps" options))

(defn get-prioritised-transactions
  [rpc-config]
  (utils/rpc-call rpc-config "getprioritisedtransactions"))

(defn prioritise-transaction
  [rpc-config transaction-id fee-delta]
  (utils/rpc-call rpc-config "prioritisetransaction" [transaction-id nil fee-delta]))

(defn submit-block
  [rpc-config block-hex]
  (utils/rpc-call rpc-config "submitblock" [block-hex]))

(defn submit-header
  [rpc-config header-hex]
  (utils/rpc-call rpc-config "submitheader" [header-hex]))
