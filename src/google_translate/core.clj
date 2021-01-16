(ns google-translate.core
  (:import [com.google.cloud.translate Translate Translate$TranslateOption TranslateOptions Language]
           [java.lang.reflect Array]))

(defn- get-service-v1 []
  "Get service using GOOGLE_APPLICATION_CREDENTIALS environment var"
  (-> (TranslateOptions/getDefaultInstance)
      (.getService)))

(defn- get-service-v2 []
  "Get service using and api-key"
  (let [api-key (-> "config/creds.edn" slurp read-string :api-key)]
    (-> (TranslateOptions/newBuilder)
        (.setApiKey api-key)
        (.build)
        (.getService))))

(def ^:dynamic *get-service* get-service-v1)

(defn use-api-key []
  "Configure to use api key as creds"
    (alter-var-root (var *get-service*) (constantly get-service-v2)))

(defn use-env-var-creds []
  "Configure to use environment variable as creds"
    (alter-var-root (var *get-service*) (constantly get-service-v1)))

#_(def detection (.detect translate "hola"))

(defn language-opts [& opts]
  (let [{:keys [from to]} (apply hash-map (first opts))
        lang-opts (Array/newInstance Translate$TranslateOption 2)]
    (do
      (aset lang-opts 0 (Translate$TranslateOption/sourceLanguage from))
      (aset lang-opts 1 (Translate$TranslateOption/targetLanguage to)))
    lang-opts))

(defn translate! [text & opts]
  (let [translate (*get-service*)
        lang-opts (if opts (language-opts opts) (language-opts))]
    (-> (.translate translate text lang-opts) (.getTranslatedText))))

(comment
  
  (translate! "hola mundo")

  (translate! "hola mundo" :to "it" :from "es")

  (translate! "Sono le mia mele" :from "it" :to "en")
  (translate! "They are my apples" :to "it")
  (translate! "My cats drink milk" :to "it")
  (translate! "Their dog does not eat candy" :to "it")
  (translate! "Your lion eats my steak" :to "it")
  

  )


