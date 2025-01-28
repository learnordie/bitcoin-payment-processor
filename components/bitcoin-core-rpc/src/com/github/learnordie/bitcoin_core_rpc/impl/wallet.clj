;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.impl.wallet
  (:require [com.github.learnordie.bitcoin-core-rpc.utils :as utils]
            [com.github.learnordie.logging.interface :as log]))

(defn abandon-transaction
  [rpc-config wallet-name transaction-id]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "abandontransaction" [transaction-id]))

(defn abort-rescan
  [rpc-config wallet-name]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "abortrescan"))

(defn add-multisig-address
  [rpc-config wallet-name nrequired keys options]
  (let [params (-> options
                   (assoc :nrequired nrequired
                          :keys keys))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "addmultisigaddress" params)))

(defn backup-wallet
  [rpc-config wallet-name destination]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "backupwallet" [destination]))

(defn bump-fee
  [rpc-config wallet-name transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "bumpfee" params)))

(defn create-wallet
  [rpc-config wallet-name options]
  (let [params (-> options
                   (assoc :wallet-name wallet-name))]
    (utils/rpc-call rpc-config "createwallet" params)))

(defn create-wallet-descriptor
  [rpc-config wallet-name address-type options]
  (let [params (-> options
                   (assoc :type address-type)
                   (dissoc :address-type))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "createwalletdescriptor" params)))

(defn dump-private-key
  [rpc-config wallet-name address]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "dumpprivkey" [address]))

(defn dump-wallet
  [rpc-config wallet-name filename]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "dumpwallet" [filename]))

(defn encrypt-wallet
  [rpc-config wallet-name passphrase]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "encryptwallet" [passphrase]))

(defn get-addresses-by-label
  [rpc-config wallet-name label]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getaddressesbylabel" [label]))

(defn get-address-info
  [rpc-config wallet-name address]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getaddressinfo" [address]))

(defn get-balance
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getbalance" options))

(defn get-balances
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getbalances" options))

(defn get-hd-keys
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "gethdkeys" options))

(defn get-new-address
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getnewaddress" options))

(defn get-raw-change-address
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getrawchangeaddress" options))

(defn get-received-by-address
  [rpc-config wallet-name address options]
  (let [params (-> options
                   (assoc :address address))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getreceivedbyaddress" params)))

(defn get-received-by-label
  [rpc-config wallet-name label options]
  (let [params (-> options
                   (assoc :label label))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "getreceivedbylabel" params)))

(defn get-transaction
  [rpc-config wallet-name transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "gettransaction" params)))

(defn get-wallet-info
  [rpc-config wallet-name]
  (utils/transform-keys-to-kebab-case
   (utils/rpc-call-for-wallet-functions rpc-config rpc-config wallet-name "getwalletinfo")))

(defn import-address
  [rpc-config wallet-name address options]
  (log/info "This RPC call to `importaddress` can take over an hour to complete if `rescan` is true.")
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (assoc :address address))
        ]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importaddress" params)))

(defn import-descriptors
  [rpc-config wallet-name requests]
  (log/info "This RPC call to `importdescriptors` can take over an hour to complete if using an early timestamp.")
  (let [rpc-config (if (:timeout rpc-config)
                     rpc-config
                     (assoc rpc-config :timeout 7200000))] ; 2 hours in milliseconds
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importdescriptors" [requests])))

(defn import-multi
  [rpc-config wallet-name requests options]
  (log/info "This RPC call to `importmulti` can take over an hour to complete if `rescan` is true.")
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (assoc :requests requests))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importmulti" params)))

(defn import-private-key
  [rpc-config wallet-name private-key options]
  (log/info "This RPC call to `importprivkey` can take over an hour to complete if `rescan` is true.")
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (assoc :privkey private-key))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importprivkey" params)))

(defn import-pruned-funds
  [rpc-config wallet-name raw-transaction transaction-out-proof]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importprunedfunds" [raw-transaction transaction-out-proof]))

(defn import-public-key
  [rpc-config wallet-name public-key options]
  ;; This RPC call can take over an hour to complete if `rescan` is true.
  (log/info "This RPC call to `importpubkey` can take over an hour to complete if `rescan` is true.")
  (let [rpc-config (if (and (:rescan options) (not (:timeout rpc-config)))
                     rpc-config
                     (assoc rpc-config :timeout 7200000)) ; 2 hours in milliseconds
        params (-> options
                   (assoc :pubkey public-key))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importpubkey" params)))

(defn import-wallet
  [rpc-config wallet-name filename]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "importwallet" [filename]))

(defn keypool-refill
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "keypoolrefill" options))

(defn list-address-groupings
  [rpc-config wallet-name]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listaddressgroupings"))

(defn list-descriptors
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listdescriptors" options))

(defn list-labels
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listlabels" options))

(defn list-lock-unspent
  [rpc-config wallet-name]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listlockunspent"))

(defn list-received-by-address
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listreceivedbyaddress" options))

(defn list-received-by-label
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listreceivedbylabel" options))

(defn list-since-block
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listsinceblock" options))

(defn list-transactions
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listtransactions" options))

(defn list-unspent
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "listunspent" options))

(defn list-wallet-dir
  [rpc-config]
  (utils/rpc-call rpc-config "listwalletdir"))

(defn list-wallets
  [rpc-config]
  (utils/rpc-call rpc-config "listwallets"))

(defn load-wallet
  [rpc-config filename options]
  (let [params (-> options
                   (assoc :filename filename))]
    (utils/rpc-call rpc-config "loadwallet" params)))

(defn lock-unspent
  [rpc-config wallet-name unlock options]
  (let [params (-> options
                   (assoc :unlock unlock))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "lockunspent" params)))

(defn migrate-wallet
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "migratewallet" options))

(defn new-keypool
  [rpc-config wallet-name]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "newkeypool"))

(defn psbt-bump-fee
  [rpc-config wallet-name transaction-id options]
  (let [params (-> options
                   (assoc :txid transaction-id))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "psbtbumpfee" params)))

(defn remove-pruned-funds
  [rpc-config wallet-name transaction-id]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "removeprunedfunds" [transaction-id]))

(defn rescan-blockchain
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "rescanblockchain" options))

(defn restore-wallet
  [rpc-config wallet-name backup-file options]
  (let [params (-> options
                   (assoc :wallet-name wallet-name
                          :backup-file backup-file))]
    (utils/rpc-call rpc-config "restorewallet" params)))

(defn send
  [rpc-config wallet-name outputs options]
  (let [params (-> options
                   (assoc :outputs outputs))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "send" params)))

(defn send-all
  [rpc-config wallet-name recipients options]
  (let [params (-> options
                   (assoc :recipients recipients))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "sendall" params)))

(defn send-many
  [rpc-config wallet-name amounts options]
  (let [params (-> options
                   (assoc :amounts amounts))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "sendmany" params)))

(defn send-to-address
  [rpc-config wallet-name address amount options]
  (let [params (-> options
                   (assoc :address address
                          :amount amount))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "sendtoaddress" params)))

(defn set-hd-seed
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "sethdseed" options))

(defn set-label
  [rpc-config wallet-name address label]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "setlabel" [address label]))

(defn set-transaction-fee
  [rpc-config wallet-name amount]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "settxfee" [amount]))

(defn set-wallet-flag
  [rpc-config wallet-name flag options]
  (let [params (-> options
                   (assoc :flag flag))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "setwalletflag" params)))

(defn sign-message
  [rpc-config wallet-name address message]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "signmessage" [address message]))

(defn sign-raw-transaction-with-wallet
  [rpc-config wallet-name hex-string options]
  (let [params (-> options
                   (assoc :hexstring hex-string))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "signrawtransactionwithwallet" params)))

(defn simulate-raw-transaction
  [rpc-config wallet-name options]
  ;; https://bitcoincore.org/en/doc/28.0.0/rpc/wallet/simulaterawtransaction/
  ;; Documentation mentions that `rawtxs` is an optional parameter, but if not provided,
  ;; it returns this error: "JSON value of type null is not of expected type array"
  ;; So we'll provide an empty array as the default value.
  (let [params (if (nil? options)
                 {:rawtxs []}
                 options)]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "simulaterawtransaction" params)))

(defn unload-wallet
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "unloadwallet" options))

(defn upgrade-wallet
  [rpc-config wallet-name options]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "upgradewallet" options))

(defn wallet-create-funded-psbt
  [rpc-config wallet-name outputs options]
  (let [params (-> options
                   (assoc :outputs outputs))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletcreatefundedpsbt" params)))

(defn wallet-display-address
  [rpc-config wallet-name address]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletdisplayaddress" [address]))

(defn wallet-lock
  [rpc-config wallet-name]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletlock"))

(defn wallet-passphrase
  [rpc-config wallet-name passphrase timeout]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletpassphrase" [passphrase timeout]))

(defn wallet-passphrase-change
  [rpc-config wallet-name old-passphrase new-passphrase]
  (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletpassphrasechange" [old-passphrase new-passphrase]))

(defn wallet-process-psbt
  [rpc-config wallet-name psbt options]
  (let [params (-> options
                   (assoc :psbt psbt))]
    (utils/rpc-call-for-wallet-functions rpc-config wallet-name "walletprocesspsbt" params)))
