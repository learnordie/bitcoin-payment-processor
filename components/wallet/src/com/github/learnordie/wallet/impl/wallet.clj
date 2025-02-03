;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.wallet)

(defn create-hot-wallet
  [network]
  {:wallet-type :hot
   :network network
   :seed "seed"
   :xprv "xprv"
   :xpub "xpub"})

(defn create-watch-only-wallet
  [network]
  {:wallet-type :watch-only
   :network network
   :xpub "xpub"})
