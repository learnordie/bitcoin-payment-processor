;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.utils
  (:require [clojure.string :as str]))

(set! *warn-on-reflection* true)

(defn- unsigned-byte
  "Ensures the byte is treated as an unsigned 8-bit value"
  [byte]
  (bit-and 0xFF byte))

(defn- pad-binary-string
  "Ensures the binary string is zero-padded to the specified length."
  [binary-str length]
  (if (>= (count binary-str) length)
    binary-str
    (let [formatter (str "%0" length "d")]
      (format formatter (Integer/parseInt binary-str)))))

(defn- byte->binary-string
  "Converts a single byte to its 8-bit binary string representation, ensuring proper padding."
  [byte]
  (pad-binary-string (Integer/toBinaryString (unsigned-byte byte)) 8))

(defn byte-seq->binary-string
  "Converts a byte sequence to a binary string."
  [bytes]
  (str/join (map byte->binary-string bytes)))

(defn binary-seq->int
  "Converts a sequence of binary characters (\0, \1) into an integer."
  [binary-seq]
  (Integer/parseInt (str/join binary-seq) 2))

(defn- word->index
  "Computes the index of a word in the wordlist.

  Returns -1 if the word is not found."
  [word wordlist]
  (.indexOf ^java.util.List wordlist ^String word))

(defn- word->binary-string
  "Converts a word to its binary representation."
  [word wordlist]
  (let [index (word->index word wordlist)]
    (when-not (neg? index)
      (pad-binary-string (Integer/toBinaryString index) 11))))

(defn word-seq->binary-string
  "Converts a sequence of words to a binary string."
  [words wordlist]
  (str/join (map #(word->binary-string % wordlist) words)))

(defn- binary-string->byte
  "Converts a single 8-bit binary string to a byte."
  [binary-str]
  (let [unsigned-int (Integer/parseInt binary-str 2)]
    (if (>= unsigned-int 128)
      (- unsigned-int 256)
      unsigned-int)))

(defn- binary-chunks
  "Splits a binary string into 8 bit chunks."
  [binary-str]
  (map str/join (partition 8 binary-str)))

(defn binary-string->byte-seq
  "Converts a binary string to a sequence of bytes."
  [binary-str]
  (let [binary-str-groups (binary-chunks binary-str)]
    (byte-array (map binary-string->byte binary-str-groups))))

(defn- byte->hex
  "Converts a byte to its two-character, zero-padded hexadecimal representation."
  [byte]
  (format "%02x" (unsigned-byte byte)))

(defn byte-seq->hex-string
  "Converts a byte sequence to a hexadecimal string."
  [bytes]
  (str/join (map byte->hex bytes)))

(defn str->bytes
  "Converts a string to a byte sequence."
  [string]
  (.getBytes ^String string "UTF-8"))
