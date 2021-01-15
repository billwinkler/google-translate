(ns google-translate.core
  (:import [com.google.cloud.translate Translate Translate$TranslateOption TranslateOptions Language]))

(def ^:private api-key (-> "config/creds.edn" slurp read-string :api-key))

(def options (TranslateOptions/getDefaultInstance))

(def translate (.getService options))
(def translate (-> (TranslateOptions/newBuilder)
                   (.setApiKey api-key)
                   (.build)
                   (.getService)))

(defn get-service []
  (-> (TranslateOptions/newBuilder)
      (.setApiKey api-key)
      (.build)
      (.getService)))

#_(def detection (.detect translate "hola"))


;; these also work
;; (.getLanguage detection)
;; (.detect translate ["Hello, World!", "¡Hola Mundo!"])


(def opts (java.lang.reflect.Array/newInstance Translate$TranslateOption 2))
(aset opts 0 (Translate$TranslateOption/sourceLanguage "es"))
(aset opts 1 (Translate$TranslateOption/targetLanguage "en"))

(def opts (let [opts (java.lang.reflect.Array/newInstance Translate$TranslateOption 2)]
            (aset opts 0 (Translate$TranslateOption/sourceLanguage "es"))
            (aset opts 1 (Translate$TranslateOption/targetLanguage "it"))
            opts))

(def translation (.translate translate "¡Hola Mundo!" opts))

(defn translate [text]
  (let [translate (get-service)]
    (-> (.translate translate text opts) (.getTranslatedText))))

(translate "hola mundo")


