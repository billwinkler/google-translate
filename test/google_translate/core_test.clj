(ns google-translate.core-test
  (:require [clojure.test :refer :all]
            [google-translate.core :refer :all]))

(deftest test-authentication-method
  (testing "default env and api authentication methods"
    (let [api-method (set-authentication-method! :api-key)
          env-method (set-authentication-method! :env)
          def-method (set-authentication-method!)]
      (is (= def-method env-method) "environment var is default")
      (is (not= api-method env-method) "api method was set"))))


(deftest test-detect
  (testing "language detection api"
    (is (= "es" (:language (detect "hola"))))))


(deftest test-translate!
  (testing "translation api"
    (are [text translation from to] (= translation (translate! text :from from :to to))
      "Hola mundo" "Hello World" "es" "en"
      "Hola mundo" "Hello World" "es" nil
      "Hola mundo" "Hello World" nil "en"
      "Hola mundo" "Hello World" nil nil
      "Hola mundo" "Ciao mondo" nil "it")))

