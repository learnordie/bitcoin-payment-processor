;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.signer
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]))

(defn enumerate-signers
  [rpc-config]
  (utils/rpc-call rpc-config "enumeratesigners"))
