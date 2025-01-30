;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.interface-test
  (:require [clojure.test :as t]
            [com.github.learnordie.wallet.interface :as sut]))

(t/deftest create-hot-wallet
  (t/is (= {:wallet-type :hot
            :network :mainnet
            :seed "seed"
            :xprv "xprv"
            :xpub "xpub"}
           (sut/create-hot-wallet))))

(t/deftest create-watch-only-wallet
  (t/is (= {:wallet-type :watch-only
            :network :mainnet
            :xpub "xpub"}
           (sut/create-watch-only-wallet))))
