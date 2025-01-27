;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.control
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn get-memory-info
  [rpc-config options]
  (utils/rpc-call rpc-config "getmemoryinfo" options))

(defn get-rpc-info
  [rpc-config]
  (utils/rpc-call rpc-config "getrpcinfo"))

(defn help
  [rpc-config options]
  (utils/rpc-call rpc-config "help" options))

(defn logging
  [rpc-config options]
  (utils/rpc-call rpc-config "logging" options))

(defn stop
  [rpc-config]
  (utils/rpc-call rpc-config "stop"))

(defn uptime
  [rpc-config]
  (utils/rpc-call rpc-config "uptime"))

