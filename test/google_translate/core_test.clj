(ns google-translate.core-test
  (:require [clojure.test :refer :all]
            [google-translate.core :refer :all]))

(deftest test-api-key
  (testing "use api key"
    (let [result (use-api-key)]
      (is (= *get-service* result)))))

(deftest test-env-var-creds
  (testing "use environment variable as creds"
    (let [result (use-env-var-creds)]
      (is (= *get-service* result)))))

(deftest test-translate!
  (testing "translations"
    (are [text translation from to] (= translation (translate! text :from from :to to))
      "Hola mundo" "Hello World" "es" "en"
      "Hola mundo" "Hello World" "es" nil
      "Hola mundo" "Hello World" nil "en"
      "Hola mundo" "Hello World" nil nil
      "Hola mundo" "Ciao mondo" nil "it")))
