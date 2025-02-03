;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.bip39-test
  (:require  [clojure.test :as t]
             [com.github.learnordie.wallet.impl.bip39 :as sut]))

(t/deftest generate-mnemonic
  (t/is (= :not-implemented (sut/generate-mnemonic))))

(t/deftest valid-mnemonic?
  (t/is (= :not-implemented (sut/valid-mnemonic? ["foo" "bar" "baz"]))))

(t/deftest mnemonic->seed
  (t/is (= :not-implemented (sut/mnemonic->seed ["foo" "bar" "baz"] "passphrase"))))
