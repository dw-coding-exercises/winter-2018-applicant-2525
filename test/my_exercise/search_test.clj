(ns my-exercise.search-test
  (:require [clojure.test :refer :all]
            [my-exercise.search :as search]))

(deftest format-params-test
  (testing "param is converted to lower case"
    (is (= "ca"
           (search/format-param "CA"))))
  (testing "param has spaces converted to underscores"
    (is (= "san_luis_obispo"
           (search/format-param "San Luis Obispo"))))
  (testing "param has leading and trailing whitespace removed"
    (is (= "humuhumunukunukuapua`a"
           (search/format-param "   humuhumunukunukuapua`a   ")))))

(deftest build-partial-ocdid-url-test
  (testing "state ocdid portion of url"
    (is (= "/state:ca"
           (search/build-state-ocdid {:params {:state "CA"}}))))

  (testing "city ocdid portion of url if city provided"
    (is (= "/place:san_francisco"
           (search/build-city-ocdid {:params {:city "San Francisco"}}))))

  (testing "city ocdid portion of url if no city provided"
    (is (= ""
           (search/build-city-ocdid {:params {:city ""}})))))

(deftest build-query-params-test
  (testing "query params includes both state and place"
    (is (= "ocd-division/country:us/state:ca,ocd-division/country:us/state:ca/place:san_francisco"
           (search/build-query-params {:params {:state "CA" :city "San Francisco"}}))))

  (testing "query params include state only when no city param"
    (is (= "ocd-division/country:us/state:ca"
           (search/build-query-params {:params {:state "CA"}})))))
