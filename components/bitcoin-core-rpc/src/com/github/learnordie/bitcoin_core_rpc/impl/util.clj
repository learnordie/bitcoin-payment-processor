;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.util
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn create-multisig
  [rpc-config nrequired keys options]
  (let [params (-> options
                   (assoc :nrequired nrequired
                          :keys keys))]
    (utils/rpc-call rpc-config "createmultisig" params)))

(defn derive-addresses
  [rpc-config descriptor options]
  (let [params (-> options
                   (assoc :descriptor descriptor))]
    (utils/rpc-call rpc-config "deriveaddresses" params)))

(defn estimate-smart-fee
  [rpc-config conf-target options]
  (let [params (-> options
                   (assoc :conf_target conf-target))]
    (utils/rpc-call rpc-config "estimatesmartfee" params)))

(defn get-descriptor-info
  [rpc-config descriptor]
  (utils/rpc-call rpc-config "getdescriptorinfo" [descriptor]))

(defn get-index-info
  [rpc-config options]
  (utils/rpc-call rpc-config "getindexinfo" options))

(defn sign-message-with-private-key
  [rpc-config private-key message]
  (utils/rpc-call rpc-config "signmessagewithprivkey" [private-key message]))

(defn validate-address
  [rpc-config address]
  (utils/rpc-call rpc-config "validateaddress" [address]))

(defn verify-message
  [rpc-config address signature message]
  (utils/rpc-call rpc-config "verifymessage" [address signature message]))
