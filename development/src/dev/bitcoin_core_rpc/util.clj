;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.util
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (let [nrequired 2
        pubkey-1 "03789ed0bb717d88f7d321a368d905e7430207ebbd82bd342cf11ae157a7ace5fd"
        pubkey-2 "03dbc6764b8884a92e871274b87583e6d5c2a58819473e17e107ef3f6aa5a61626"]
    (btc/create-multisig config nrequired [pubkey-1 pubkey-2]))

  (let [descriptor "wpkh([1bbae073/84h/1h/0h]tpubDDFt5PsGLwNi4sTksdqgaEZuvFsbG7C6y6FaWit6wuvPRuWHHcoiaKFBD6MnqnFwQRa8PnTGydF3Wg1hA2mhMPMgtEFMrW4vSLAqsAb2UMH/0/*)#a7vyjt27"]
    (btc/derive-addresses config descriptor :range [0 2]))

  (btc/estimate-smart-fee config 6)

  (let [descriptor "wpkh([1bbae073/84h/1h/0h]tpubDDFt5PsGLwNi4sTksdqgaEZuvFsbG7C6y6FaWit6wuvPRuWHHcoiaKFBD6MnqnFwQRa8PnTGydF3Wg1hA2mhMPMgtEFMrW4vSLAqsAb2UMH/0/*)#a7vyjt27"]
    (btc/get-descriptor-info config descriptor))

  (btc/get-index-info config :index-name "txindex")

  (let[private-key "private-key"
       message "Hello, World!"]
    (btc/sign-message-with-private-key config private-key message))

  (let [address "bcrt1q6fm2zcj3f63w4av3up62cs39w0tuw4m74ftnzv"]
    (btc/validate-address config address))

  (let [address "bcrt1q6fm2zcj3f63w4av3up62cs39w0tuw4m74ftnzv"
        signature "signature"
        message "Hello, World!"]
    (btc/verify-message config address signature message))

  )
