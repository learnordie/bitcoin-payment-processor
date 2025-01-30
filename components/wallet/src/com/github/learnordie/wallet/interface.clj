;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.interface
  (:require [com.github.learnordie.wallet.impl :as impl]))

(defn create-hot-wallet
  "Creates a hot wallet."
  ([]
   (create-hot-wallet :mainnet))
  ([network]
   (impl/create-hot-wallet network)))

(defn create-watch-only-wallet
  "Creates a watch-only wallet."
  ([]
   (create-watch-only-wallet :mainnet))
  ([network]
   (impl/create-watch-only-wallet network)))
