;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.network
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn add-node
  [rpc-config node command options]
  (let [params (-> options
                   (assoc :node node
                          :command command))]
    (utils/rpc-call rpc-config "addnode" params)))

(defn clear-banned
  [rpc-config]
  (utils/rpc-call rpc-config "clearbanned"))

(defn disconnect-node
  [rpc-config options]
  (utils/rpc-call rpc-config "disconnectnode" options))

(defn get-added-node-info
  [rpc-config options]
  (utils/rpc-call rpc-config "getaddednodeinfo" options))

(defn get-address-manager-info
  [rpc-config]
  (utils/rpc-call rpc-config "getaddrmaninfo"))

(defn get-connection-count
  [rpc-config]
  (utils/rpc-call rpc-config "getconnectioncount"))

(defn get-network-totals
  [rpc-config]
  (utils/rpc-call rpc-config "getnettotals"))

(defn get-network-info
  [rpc-config]
  (utils/rpc-call rpc-config "getnetworkinfo"))

(defn get-node-addresses
  [rpc-config options]
  (utils/rpc-call rpc-config "getnodeaddresses" options))

(defn get-peer-info
  [rpc-config]
  (utils/rpc-call rpc-config "getpeerinfo"))

(defn list-banned
  [rpc-config]
  (utils/rpc-call rpc-config "listbanned"))

(defn ping
  [rpc-config]
  (utils/rpc-call rpc-config "ping"))

(defn set-ban
  [rpc-config subnet command options]
  (let [params (-> options
                   (assoc :subnet subnet
                          :command command))]
    (utils/rpc-call rpc-config "setban" params)))

(defn set-network-active
  [rpc-config state]
  (utils/rpc-call rpc-config "setnetworkactive" [state]))

