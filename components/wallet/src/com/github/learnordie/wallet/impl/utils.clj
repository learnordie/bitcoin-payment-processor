;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.wallet.impl.utils)

(defn- unsigned-byte
  "Ensures the byte is treated as an unsigned 8-bit value"
  [byte]
  (bit-and 0xFF byte))

(defn- pad-binary-string
  "Ensures the binary string is zero-padded to 8 bits."
  [binary-str]
  (format "%08d" (Integer/parseInt binary-str)))

(defn- byte->binary-string
  "Converts a single byte to its 8-bit binary string representation, ensuring proper padding."
  [byte]
  (pad-binary-string (Integer/toBinaryString (unsigned-byte byte))))

(defn byte-seq->binary-string
  "Converts a byte sequence to a binary string."
  [bytes]
  (apply str (map byte->binary-string bytes)))

(defn binary-seq->int
  "Converts a sequence of binary characters (\0, \1) into an integer."
  [binary-seq]
  (Integer/parseInt (apply str binary-seq) 2))
