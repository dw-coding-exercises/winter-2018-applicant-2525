(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [clj-http.client :as client]
            [clojure.string :as string]))

(def base-url "https://api.turbovote.org/elections/upcoming")

(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "Upcoming Elections"]
   [:link {:rel "stylesheet" :href "default.css"}]])

(defn results [_]
  [:div {:class "election-results"}
   [:h1 "Upcoming Elections"]
   [:p "This is a placeholder for the results."]])

(defn format-param [param]
  "Trim leading/trailing spaces, replace spaces with underscore, convert to lowercase"
  (string/lower-case (string/replace (string/trim param) #" " "_")))

(defn build-state-ocdid [request]
  "Build state portion of the query params"
  (let [state (get (:params request) :state)]
    (str "/state:" (format-param state))))

(defn build-city-ocdid [request]
  "Build city portion of the query params"
  (let [city (get (:params request) :city)]
    ; If city param not provided return empty string
    (if (not (string/blank? city)) (str "/place:" (format-param city)) "")
    ))

(defn build-query-params [request]
  "Build the api query params"
  (let [country "ocd-division/country:us"
        state-url (build-state-ocdid request)
        city-url (build-city-ocdid request)]
    ; If city-url is blank, do not append to query url
    (str country state-url (if (not (string/blank? city-url)) (str "," country state-url city-url)))))

(defn do-search [request]
  (try
    (client/get
      base-url {:query-params {:district-divisions (build-query-params request)}} {:accept :json})
    (catch Exception e
      (println "caught" e))))

(defn page [request]
  (html5
    (header request)
    (results request)
    (do-search request)))
