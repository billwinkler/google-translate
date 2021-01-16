# google-translate

A clojure wrapper for the
[Google Translate API](https://googleapis.dev/java/google-cloud-translate/latest/index.html
"Google Translate Javadocs").

## Install
Add the following dependency to your `project.clj` file:

    [google-translate "0.1.0"]

## Credentials

Follow link to set up credentials, billing, etc...

<https://cloud.google.com/translate/docs/setup>

You'll need credentials to make api calls. These can be specified via
an environment variable where `[PATH]` is the path to the json
credentials for a particular google project.  This is the project
where any charges will be accounted for.

``` shell
export GOOGLE_APPLICATION_CREDENTIALS="[PATH]"
```

You can use an `api-key` as an alternative.

``` clojure
{:api-key "your api key"}
```

Store (and gitignore) it in `../config/creds.edn`. Then set the authentication method to use an `api-key` instead of the environment variable.


``` clojure
(set-authentication-method! :api-key)
```

## Usage

``` clojure
(detect "hola") => {:language "es", :confidence 0.91015625}

(translate! "hola mundo") => "Hello World"
(translate! "hola mundo" :from "es" :to "it") => "Ciao mondo"
```




## Javadocs)

<https://googleapis.dev/java/google-cloud-translate/latest/index.html>

<https://github.com/googleapis/java-translate>


## License

Copyright © 2021 Bill Winkler

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
