;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.bitcoin-core-rpc.utils
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [cheshire.core :as json]
            [hato.client :as hc]))


(defn transform-keys-to-kebab-case
  "Transforms the keys of a map to kebab case."
  [m]
  ;; Using a separator of \_ to avoid problems when digits are present in the keys.
  ;; https://github.com/clj-commons/camel-snake-kebab/issues/31#issuecomment-90229445
  (cske/transform-keys #(csk/->kebab-case % :separator \_) m))

(defn- transform-keys-to-snake-case
  ;; Using a separator of \- to avoid problems when digits are present in the keys.
  ;; https://github.com/clj-commons/camel-snake-kebab/issues/31#issuecomment-90229445
  "Transforms the keys of a map to snake case."
  [m]
  (cske/transform-keys #(csk/->snake_case % :separator \-) m))

(defn rpc-call
  "Calls a Bitcoin Core RPC method with the given parameters."
  ([rpc-config method]
   (rpc-call rpc-config method []))
  ([{:keys [url rpc-user rpc-password]} method params]
   (let [params (transform-keys-to-snake-case params)
         payload {:jsonrpc "2.0"
                  :id (rand-int 1000000)
                  :method method
                  :params params}
         response (hc/post url
                           {:content-type :json
                            :body (json/generate-string payload)
                            :as :json
                            :basic-auth {:user rpc-user :pass rpc-password}})
         body (:body response)]
     (if-let [error (:error body)]
       (throw (ex-info (str "Error: " error) response))
       (-> (:result body) transform-keys-to-kebab-case)))))

(defn rpc-call-for-wallet-functions
  "Calls a Bitcoin Core RPC method with the given parameters for a specific wallet."
  ([rpc-config wallet-name method]
   (rpc-call-for-wallet-functions rpc-config wallet-name method []))
  ([rpc-config wallet-name method params]
   (let [url (str (:url rpc-config) "/wallet/" wallet-name)
         rpc-config (assoc rpc-config :url url)]
     (rpc-call rpc-config method params))))
