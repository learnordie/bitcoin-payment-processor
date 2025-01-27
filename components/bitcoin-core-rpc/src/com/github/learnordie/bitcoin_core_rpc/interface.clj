;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.interface
  (:require [com.github.learnordie.bitcoin-core-rpc.impl.blockchain :as blockchain]))

(defn get-blockchain-info
  "Returns an object containing various state info regarding blockchain processing."
  [rpc-config]
  (blockchain/get-blockchain-info rpc-config))
