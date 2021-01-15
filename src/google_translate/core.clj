(ns google-translate.core
  (:import [com.google.cloud.translate Translate Translate$TranslateOption TranslateOptions Language]
           [java.lang.reflect Array]))

(defn- get-service-v1 []
  (-> (TranslateOptions/getDefaultInstance)
      (.getService)))

(defn- get-service-v2 []
  (let [api-key (-> "config/creds.edn" slurp read-string :api-key)])
  (-> (TranslateOptions/newBuilder)
      (.setApiKey api-key)
      (.build)
      (.getService)))

(def get-service get-service-v1)

#_(def detection (.detect translate "hola"))

(defn language-opts [& opts]
  (let [{:keys [from to]} (apply hash-map (first opts))
        lang-opts (Array/newInstance Translate$TranslateOption 2)]
    (do
      (aset lang-opts 0 (Translate$TranslateOption/sourceLanguage from))
      (aset lang-opts 1 (Translate$TranslateOption/targetLanguage to)))
    lang-opts))

(defn translate! [text & opts]
  (let [translate (get-service-v1)
        lang-opts (if opts (language-opts opts) (language-opts))]
    (-> (.translate translate text lang-opts) (.getTranslatedText))))

(comment
  
  (translate! "hola mundo")

  (translate! "hola mundo" :to "it" :from "es"))


