;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.bip39
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [com.github.learnordie.logging.interface :as log]
            [com.github.learnordie.wallet.impl.utils :as utils])
  (:import [java.security MessageDigest NoSuchAlgorithmException SecureRandom]))

(set! *warn-on-reflection* true)

;;; Wordlist

(def ^:private wordlist
  (-> "wallet/bip39-wordlist.txt"
      io/resource
      slurp
      str/split-lines))

;;; Entropy generation

(defn- secure-random-number-generator
  "Creates a secure random number generator."
  []
  (try
    (SecureRandom/getInstanceStrong)
    (catch NoSuchAlgorithmException e
      (log/warn (str "Strong secure random algorithm not available: "
                     (.getMessage e)
                     ". Falling back to default algorithm."))
      (SecureRandom.))))

(defn- random-bytes
  "Generates a random byte array of the specified length in bytes."
  [length]
  (let [rng (secure-random-number-generator)
        bytes (byte-array length)]
    (SecureRandom/.nextBytes rng bytes)
    bytes))

(defn- generate-entropy
  "Generates entropy of the specified number of bits."
  [num-bits]
  (let [num-bytes (quot num-bits 8)]
    (random-bytes num-bytes)))

;;; Mnemonic generation

(defn- compute-sha256
  "Computes the SHA-256 hash of the specified byte sequence."
  [bytes]
  (let [message-digest (MessageDigest/getInstance "SHA-256")]
    (.digest message-digest bytes)))

(defn- bip39-checksum
  "Computes the BIP-39 checksum for the specified entropy."
  [entropy]
  (let [entropy-bits (* 8 (count entropy))
        checksum-bits (quot entropy-bits 32)
        hash (compute-sha256 entropy)
        hash-bits (utils/byte-seq->binary-string hash)]
    (subs hash-bits 0 checksum-bits)))

(defn entropy->mnemonic
  "Converts entropy to a mnemonic."
  [entropy]
  (let [entropy-bits (utils/byte-seq->binary-string entropy)
        checksum-bits (bip39-checksum entropy)
        full-bits (str entropy-bits checksum-bits)
        bit-groups (partition 11 full-bits)
        word-indices (map utils/binary-seq->int bit-groups)
        mnemonic-words (map #(nth wordlist %) word-indices)]
    (str/join " " mnemonic-words)))

(defn generate-mnemonic
  "Generates a mnemonic of the specified strength in bits."
  ([]
   (generate-mnemonic 128))
  ([strength]
   (assert (contains? #{128 160 192 224 256} strength)
           "Strength must be one of 128, 160, 192, 224, or 256 bits.")
   (let [entropy (generate-entropy strength)]
     (entropy->mnemonic entropy))))

;;; Mnemonic validation

(defn- normalize-mnemonic
  "Normalizes a mnemonic by removing leading/trailing whitespace and converting to lowercase."
  [mnemonic]
  (str/trim (str/lower-case mnemonic)))

(defn- split-mnemonic
  "Splits a mnemonic into words."
  [mnemonic]
  (str/split mnemonic #"\s+"))

(defn- valid-count?
  "Checks if the word count is valid."
  [words]
  (contains? #{12 15 18 21 24} (count words)))

(defn- words-in-wordlist?
  "Checks if all words are in the BIP-39 wordlist."
  [words]
  (every? (set wordlist) words))

(defn- words->binary-string
  "Converts a sequence of words to a binary string."
  [words]
  (utils/word-seq->binary-string words wordlist))

(defn- checksum-length
  "Determines the checksum length based on the binary string length."
  [binary-str]
  (let [length (count binary-str)]
    (cond
      (= length 132) 4
      (= length 165) 5
      (= length 198) 6
      (= length 231) 7
      (= length 264) 8
      :else (throw (IllegalArgumentException. "Invalid binary string length.")))))

(defn- extract-checksum
  "Extracts the checksum from the binary string."
  [binary-str]
  (let [length (checksum-length binary-str)
        start-index (- (count binary-str) length)]
    (subs binary-str start-index)))

(defn- valid-checksum?
  "Checks if the checksum is valid."
  [binary-str]
  (let [checksum (extract-checksum binary-str)
        entropy-length-in-bits (- (count binary-str) (count checksum))
        entropy-binary-str (subs binary-str 0 entropy-length-in-bits)
        entropy (utils/binary-string->byte-seq entropy-binary-str)
        expected-checksum (bip39-checksum entropy)]
    (= checksum expected-checksum)))

(defn valid-mnemonic?
  "Checks if a mnemonic is valid."
  [mnemonic]
  (assert (string? mnemonic)
          "Mnemonic must be a string.")
  (let [normalized-mnemonic (normalize-mnemonic mnemonic)
        words (split-mnemonic normalized-mnemonic)]
    (and (valid-count? words)
         (words-in-wordlist? words)
         (valid-checksum? (words->binary-string words)))))

;;; Seed generation

(defn mnemonic->seed
  "Converts a mnemonic to a seed."
  ([mnemonic]
   (mnemonic->seed mnemonic ""))
  ([mnemonic passphrase]
   :not-implemented))
