;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.utils)

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
  (apply str (map byte->binary-string bytes)))

(defn binary-seq->int
  "Converts a sequence of binary characters (\0, \1) into an integer."
  [binary-seq]
  (Integer/parseInt (apply str binary-seq) 2))

(defn- word->index
  "Computes the index of a word in the wordlist.

  Returns -1 if the word is not found."
  [word wordlist]
  (.indexOf wordlist word))

(defn- word->binary-string
  "Converts a word to its binary representation."
  [word wordlist]
  (let [index (word->index word wordlist)]
    (if (neg? index)
      nil
      (pad-binary-string (Integer/toBinaryString index) 11))))

(defn word-seq->binary-string
  "Converts a sequence of words to a binary string."
  [words wordlist]
  (apply str (map #(word->binary-string % wordlist) words)))

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
  (map #(apply str %) (partition 8 binary-str)))

(defn binary-string->byte-seq
  "Converts a binary string to a sequence of bytes."
  [binary-str]
  (let [binary-str-groups (binary-chunks binary-str)]
    (byte-array (map binary-string->byte binary-str-groups))))
