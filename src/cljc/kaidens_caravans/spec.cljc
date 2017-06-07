(ns kaidens-caravans.spec)
;  #?(:clj
;     (:import java.util.UUID))
;  (:require
;    [clojure.spec :as s]
;    [clojure.walk :refer [postwalk]]
;    [clojure.string :as string]
;    [clojure.tools.reader :refer [read-string]]))
;
;(def ^:private explain {})
;;----------------------------------------
;; SPEC VALIDATOR AND EXPLAIN
;;----------------------------------------
;(defn- get-invalid-field
;  "Determines the invalid field of a given map structure."
;  [{:keys [path via pred]}]
;  (cond
;    (and (seq? pred)
;         (s/get-spec (last pred))) (merge via (last pred))
;    :else via))
;
;(defn- explain-error [v]
;  (or (get-in explain v) "__No error message found.__"))
;
;(defn- assoc-errors [e]
;  "Associates errors with their human readable description"
;  (->> e
;       explain-error
;       (assoc-in {} e)
;       first
;       val))
;
;(defn- explain-spec-errors [errors]
;  "Finds the human readable message for each error and produces a single map
;  of all errors"
;  (let [errs (map get-invalid-field errors)]
;    (apply merge (map assoc-errors errs))))
;
;(defn- find-problems [spec m]
;  (::s/problems (s/explain-data spec m)))
;
;(defn- pretty-format-spec [spec m]
;  (let [x
;        (->> m
;             (find-problems spec)
;             explain-spec-errors)]
;    x))
;
;(defn validate-spec
;  "Validates a spec against a provided map, returning a list of problems or nil"
;  [spec m]
;  (if (not (s/valid? spec m))
;    (pretty-format-spec spec m)
;    nil))
;
;;----------------------------------------
;; SPEC DEFINITIONS
;;----------------------------------------
;(defn- uuid-str? [s] (and (string? s) (re-matches #"(\w{8}(-\w{4}){3}-\w{12}?)$" s)))
;
;(defn- ->uuid
;  "clojure.spec conformer function to convert the argument
;  into a UUID type"
;  [s]
;  (cond
;    (uuid? s) s
;    (keyword? s) (->uuid (name s))
;    (uuid-str? s) #?(:clj  (UUID/fromString s)
;                     :cljs (UUID. s nil))
;    :else ::s/invalid))
;
;;Clojure spec predicate for a non empty string
;(def non-empty-string (s/and string? #(not= "" %)))
;
;(defn- str->int [s] #?(:cljs (cond (and (string? s) #(not= "" %)) (read-string s)
;                                   (integer? s) s
;                                   :else s)
;                       :clj  (cond (and (string? s) #(not= "" %))
;                                   (let [t (clojure.tools.reader/read-string s)]
;                                     (if (integer? t) t ::s/invalid))
;                                   (integer? s) s
;                                   :else ::s/invalid)))
;
;(def ^:private is-date? (s/and string? #(re-matches #"^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$" %)))
;(def ^:private is-integer (s/conformer str->int))
