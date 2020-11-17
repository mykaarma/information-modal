# Information Modal

## Introduction

Information Modal is vanilla JavaScript framework with SpringBoot server to show Information/Help modals in WebApplications.

## Documentation & Getting Started


## Project structure

### `api`
The Core server library, Exposes Rest Enpoints.

### `client`
Retorfit based client to interact with the service via REST API.

### `model`
Common code shared by both Client and Api (DTOs etc)

### `database`
Schema and Sample Data for the Database.

### `js`
vanilla Js framework

## How to Integrate Js in your Web Application

### Imports

* Import https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js in your html.
* Import both Js and css files present under Js folder in your html.

### Usage

* Call setFetchDataBaseUrl as soon as your application is launched. Pass your server baseUrl in this function.
* Wherever you want to use this Info Icon, add this HTML snippet.

```<div class=\"infoIcon infotooltip\" data-identifier=\"Some-Identifier-Tag\" onmouseover=\"showTitle(this)\" onclick=\"openModalPopup(this)\">?</div>
```

## Building & Running

The service depends on the following Environment Variables to be set:

* INFORMATION_MODAL_DB_HOST [defaults to localhost]
* INFORMATION_MODAL_DB_PORT [defaults to 3306]
* INFORMATION_MODAL_DB_NAME [defaults to InformationModalDB]
* INFORMATION_MODAL_DB_USERNAME [defaults to root]
* INFORMATION_MODAL_DB_PASSWORD [defaults to root]

Optionally you can also overrider the port the service runs and the log file location with 

* INFORMATION_MODAL_PORT [defaults to 8080]
* INFORMATION_MODEL_LOG_LOCATION [defaults to information_modal.log in current folder]


## Model and Client can be build using

```SHELL
  mvn clean install
```

## API can be run using

```SHELL
  mvn spring-boot:run
```

## Database Requirements
* The default persistence used is [mysql](https://www.mysql.com/)
* Database Schema is present [here](https://github.com/mykaarma/information-modal/blob/main/database/schema.sql)
* If you want to schema to auto generate add this property to application.yml - `spring.jpa.hibernate.ddl-auto: update`


## Other Requirements
*  Java 11

## Get in Touch

* Open an issue at: https://github.com/mykaarma/information-modal/issues
* Email us at: opensource@mykaarma.com

## Contributions

We welcome contributions to Information Modal. Please see our [contribution guide](https://github.com/mykaarma/information-modal/blob/main/CONTRIBUTING.md) for more details.

## License
Copyright 2020 myKaarma

Licensed under the [GNU Affero General Public License v3](https://github.com/mykaarma/information-modal/blob/main/LICENSE)

## Motivation

[Netflix OSS](https://github.com/Netflix)
