;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.raw-transactions
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (let [txid "aac377ac08782fbdb58a8e2e3b1362b9afd4a8975d50994015478042d5c2f3a0"
        vout 0
        address "bcrt1q6fm2zcj3f63w4av3up62cs39w0tuw4m74ftnzv"
        amount 1]
    (btc/create-psbt config [{:txid txid :vout vout}] [{address amount}]))

  (let [psbt "cHNidP8BAFICAAAAAaDzwtVCgEcVQJlQXZeo1K+5YhM7Lo6Ktb0veAisd8OqAAAAAAD9////AQDh9QUAAAAAFgAU0nahYlFOour1keB0rEIlc9fHV34AAAAAAAAA"]
    (btc/analyze-psbt config psbt))

  (let [psbt "cHNidP8BAFICAAAAAaDzwtVCgEcVQJlQXZeo1K+5YhM7Lo6Ktb0veAisd8OqAAAAAAD9////AQDh9QUAAAAAFgAU0nahYlFOour1keB0rEIlc9fHV34AAAAAAAAA"]
    (btc/decode-psbt config psbt))

  )

