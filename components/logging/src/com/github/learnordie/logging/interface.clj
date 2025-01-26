;; This Source Code Form is subject to the terms of the Mozilla Public
;; License, v. 2.0. If a copy of the MPL was not distributed with this
;; file, You can obtain one at https://mozilla.org/MPL/2.0/.

(ns com.github.learnordie.logging.interface
  (:require [taoensso.telemere :as t]))

(defn set-min-level
  "Sets the minimum logging level."
  [level]
  (t/set-min-level! level))

(defn debug
  "Logs a debug message."
  [message]
  (t/log! :debug message))

(defn info
  "Logs an info message."
  [message]
  (t/log! :info message))

(defn warn
  "Logs a warning message."
  [message]
  (t/log! :warn message))

(defn error
  "Logs an error message."
  [message]
  (t/log! :error message))

(defn fatal
  "Logs a fatal message."
  [message]
  (t/log! :fatal message))
