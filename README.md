# Democracy Works Coding Exercise
This is a coding exercise for a server-side web application written in Clojure, using the Ring, Compojure and Hiccup libraries. The clj-http library was also added to get the search results for upcoming elections in a particular state and/or city of the US.

The following features were added:
- Add missing /search route
- Use the submission form parameters to build url for api call
- Build basic set of OCD-IDs for state and city parameters
- Retrieve the upcoming elections from the Democracy Works election API using the OCD-IDs
- Add tests for search namespace

Additional features to add:
- Render the results of the api call appropriately
- Display message if no results for upcoming elections returned
- Refactor the build-url methods/functions to make agnostic as new parameters are accepted
- Refactor the build-query-params function to formulate the return value more elegantly (i.e. join elements of vector with ',')

## Final Thoughts
This was a fun exercise. This was my first time working with Clojure. Once you get over all of the parentheses, concepts start to gel quickly. Looking forward to working on this more. Thanks.
