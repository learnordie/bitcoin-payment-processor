;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.raw-transactions
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn analyze-psbt
  [rpc-config psbt]
  (utils/rpc-call rpc-config "analyzepsbt" [psbt]))

(defn combine-psbt
  [rpc-config transactions]
  (utils/rpc-call rpc-config "combinepsbt" [transactions]))

(defn combine-raw-transaction
  [rpc-config transactions]
  (utils/rpc-call rpc-config "combinerawtransaction" [transactions]))

(defn convert-to-psbt
  [rpc-config hex-string options]
  (let [params (-> options
                   (assoc :hexstring hex-string))]
    (utils/rpc-call rpc-config "converttopsbt" params)))

(defn create-psbt
  [rpc-config inputs outputs options]
  (let [params (-> options
                   (assoc :inputs inputs
                          :outputs outputs))]
    (utils/rpc-call rpc-config "createpsbt" params)))

(defn create-raw-transaction
  [rpc-config inputs outputs options]
  (let [params (-> options
                   (assoc :inputs inputs
                          :outputs outputs))]
    (utils/rpc-call rpc-config "createrawtransaction" params)))

(defn decode-psbt
  [rpc-config psbt]
  (utils/rpc-call rpc-config "decodepsbt" [psbt]))

(defn decode-raw-transaction
  [rpc-config hex-string options]
  (let [params (-> options
                   (assoc :hexstring hex-string))]
    (utils/rpc-call rpc-config "decoderawtransaction" params)))

(defn decode-script
  [rpc-config hex-string]
  (utils/rpc-call rpc-config "decodescript" [hex-string]))

(defn descriptor-process-psbt
  [rpc-config psbt descriptors options]
  (let [params (-> options
                   (assoc :psbt psbt
                          :descriptors descriptors))]
    (utils/rpc-call rpc-config "descriptorprocesspsbt" params)))

(defn finalize-psbt
  [rpc-config psbt options]
  (let [params (-> options
                   (assoc :psbt psbt))]
    (utils/rpc-call rpc-config "finalizepsbt" params)))

(defn fund-raw-transaction
  [rpc-config hex-string options]
  (let [params (-> options
                   (assoc :hexstring hex-string))]
    (utils/rpc-call rpc-config "fundrawtransaction" params)))

(defn get-raw-transaction
  [rpc-config transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call rpc-config "getrawtransaction" params)))

(defn join-psbts
  [rpc-config transactions]
  (utils/rpc-call rpc-config "joinpsbts" [transactions]))

(defn send-raw-transaction
  [rpc-config hex-string options]
  (let [params (-> options
                   (assoc :hexstring hex-string))]
    (utils/rpc-call rpc-config "sendrawtransaction" params)))

(defn sign-raw-transaction-with-key
  [rpc-config hex-string keys options]
  (let [params (-> options
                   (assoc :hexstring hex-string
                          :keys keys))]
    (utils/rpc-call rpc-config "signrawtransactionwithkey" params)))

(defn submit-package
  [rpc-config package options]
  (let [params (-> options
                   (assoc :package package))]
    (utils/rpc-call rpc-config "submitpackage" params)))

(defn test-mempool-accept
  [rpc-config transactions options]
  (let [params (-> options
                   (assoc :rawtxs transactions))]
    (utils/rpc-call rpc-config "testmempoolaccept" params)))

(defn utxo-update-psbt
  [rpc-config psbt options]
  (let [params (-> options
                   (assoc :psbt psbt))]
    (utils/rpc-call rpc-config "utxoupdatepsbt" params)))
