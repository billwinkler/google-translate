(defproject google-translate "0.1.1"
  :description "A wrapper for the Google Translate API"
  :url "https://github.com/billwinkler/google-translate"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [com.google.cloud/google-cloud-translate "1.95.6"]]
  :repl-options {:init-ns google-translate.core}
  :plugins [[lein-bom "0.2.0-SNAPSHOT"]]
  :bom {:import [[com.google.cloud/libraries-bom "16.2.0"]]}
  :deploy-repositories [["clojars" {:url "https://repo.clojars.org"
                                    :creds :gpg}]])
