;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns dev.bitcoin-core-rpc.wallet
  (:require [com.github.learnordie.bitcoin-core-rpc.interface :as btc]
            [com.github.learnordie.config.interface :as config]))

(comment

  (defonce config (config/read-config "dev/rpc-config.edn"))

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/abandon-transaction config "wallet-1" txid))

  (btc/abort-rescan config "wallet-1")

  (btc/add-multisig-address config "wallet-1" 2 ["bcrt1q9"])

  (btc/backup-wallet config "wallet-1" "/tmp/wallet-backup.dat")

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/bump-fee config "wallet-1" txid))

  (btc/create-wallet config "wallet-1" :avoid-reuse true)

  (btc/create-wallet-descriptor config "wallet-1" "p2sh-segwit")

  (let [address "bcrt1qw2jeh8hweqwtd3ryzp9luya8sws9e78q36ln7g"]
    (btc/dump-private-key config "wallet-1" address))

  (btc/dump-wallet config "wallet-1" "/tmp/dump")

  (btc/encrypt-wallet config "wallet-1" "foo-bar-baz")

  (btc/get-addresses-by-label config "wallet-1" "some label")

  (let [address "bcrt1qw2jeh8hweqwtd3ryzp9luya8sws9e78q36ln7g"]
    (btc/get-address-info config "wallet-1" address))

  (btc/get-balance config "wallet-1")
  (btc/get-balance config "wallet-1" :avoid-reuse true)

  (btc/get-balances config "wallet-1")

  (btc/get-hd-keys config "wallet-1" :active-only true)

  (btc/get-new-address config "wallet-1" :label "new address")

  (btc/get-raw-change-address config "wallet-1")

  (let [address "bcrt1qw2jeh8hweqwtd3ryzp9luya8sws9e78q36ln7g"]
    (btc/get-received-by-address config "wallet-1" address))

  (btc/get-received-by-label config "wallet-1" "new address")

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/get-transaction config "wallet-1" txid))

  (btc/get-wallet-info config "wallet-1")

  (let [address "bcrt1qw2jeh8hweqwtd3ryzp9luya8sws9e78q36ln7g"]
    (btc/import-address config "wallet-1" address)) ; can take over an hour to complete if `rescan` is true.

  ;; TODO (learnordie): look for valid examples
  (btc/import-descriptors config "wallet-1" [{}]) ; can take over an hour to complete if `rescan` is true.
  (btc/import-multi config "wallet-1" [{}]) ; can take over an hour to complete if `rescan` is true.

  (btc/import-private-key config "wallet-1" "some private key")

  ;; TODO (learnordie): look for a valid example
  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/import-pruned-funds config "wallet-1" txid ""))

  (let [tpub "tpubDDFt5PsGLwNi4sTksdqgaEZuvFsbG7C6y6FaWit6wuvPRuWHHcoiaKFBD6MnqnFwQRa8PnTGydF3Wg1hA2mhMPMgtEFMrW4vSLAqsAb2UMH"]
    (btc/import-public-key config "wallet-1" tpub)) ; can take over an hour to complete if `rescan` is true.

  (btc/import-wallet config "wallet-1" "/tmp/wallet-backup.dat")

  (btc/keypool-refill config "wallet-1" :newsize 100)

  (btc/list-address-groupings config "wallet-1")

  (btc/list-descriptors config "wallet-1" :private false)

  (btc/list-labels config "wallet-1")

  (btc/list-lock-unspent config "wallet-1")

  (btc/list-received-by-address config "wallet-1" :minconf 1 :include-empty true :include-watchonly true)

  (btc/list-received-by-label config "wallet-1" :minconf 1 :include-empty true :include-watchonly true)

  (btc/list-since-block config "wallet-1" :include-watchonly true)

  (btc/list-transactions config "wallet-1" :count 10 :include-watchonly true)

  (btc/list-unspent config "wallet-1" :minconf 1)

  (btc/list-wallet-dir config)

  (btc/list-wallets config)

  (btc/load-wallet config "wallet-1")

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/lock-unspent config "wallet-1" false :transactions [{:txid txid :vout 0}]))

  (btc/migrate-wallet config "wallet-1")

  (btc/new-keypool config "wallet-1")

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/psbt-bump-fee config "wallet-1" txid :conf-target 6))

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/psbt-bump-fee config "wallet-1 "txid :fee-rate 100))

  (let [txid "f2858bd7b33e399e1aeda0e3feb27daa6350562bac9f48b7ced4a1372c4ab7e4"]
    (btc/remove-pruned-funds config "wallet-1" txid))

  (btc/rescan-blockchain config "wallet-1")

  (btc/restore-wallet config "wallet-2" "/tmp/wallet-backup.dat")

  (let [address "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"]
    (btc/send config "wallet-1" {address 0.1}))

  (let [address-1 "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"
        address-2 "bcrt1quj25knd5mzn488jyngxnquf0lvg3yd2w73wduz"]
    (btc/send config "wallet-1" {address-1 0.1, address-2 0.2}))

  (let [address-1 "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"
        address-2 "bcrt1quj25knd5mzn488jyngxnquf0lvg3yd2w73wduz"]
    (btc/send-all config "wallet-1" [address-1 {address-2 0.2}]))

  (let [address-1 "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"
        address-2 "bcrt1quj25knd5mzn488jyngxnquf0lvg3yd2w73wduz"]
    (btc/send-many config "wallet-4" {address-1 0.1, address-2 0.2}))

  (let [address "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"]
    (btc/send-to-address config "wallet-1" address 0.1))

  (btc/set-hd-seed config "wallet-1")

  (let [address "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"]
    (btc/set-label config "wallet-1" address "change the label"))

  (btc/set-transaction-fee config "wallet-1" 0.0001)

  (btc/set-wallet-flag config "wallet-1" "avoid_reuse")

  (let [address "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"]
    (btc/sign-message config "wallet-1" address "some message"))

  (btc/sign-raw-transaction-with-wallet config "wallet-1" "hex string")

  (btc/simulate-raw-transaction config "wallet-1")
  (btc/simulate-raw-transaction config "wallet-1" :rawtxs [])

  (btc/unload-wallet config "wallet-1")

  (btc/upgrade-wallet config "wallet-1")

  (btc/wallet-create-funded-psbt config "wallet-1" [])

  (let [address "bcrt1q07p4fs79we656629cy4d98vuqa6x64tetj79h8"]
    (btc/wallet-display-address config "wallet-1" address))

  (btc/wallet-lock config "wallet-1")

  (btc/wallet-passphrase config "wallet-1" "foo-bar-baz" 30)

  (btc/wallet-passphrase-change config "wallet-1" "foo-bar-baz" "baz-bar-foo")

  (btc/wallet-process-psbt config "wallet-1" "psbt")

  )
