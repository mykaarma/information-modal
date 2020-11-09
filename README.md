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

## Building & Running

The service depends on the following Environment Variables to be set:

* INFORMATION_MODAL_DB_HOST [defaults to localhost]
* INFORMATION_MODAL_DB_PORT [defaults to 3306]
* INFORMATION_MODAL_DB_NAME [defaults to InformationModalDB]
* INFORMATION_MODAL_DB_USERNAME [defaults to root]
* INFORMATION_MODAL_DB_PASSWORD [defaults to root]

Optionally you can also overrider the host and port the service runs on

* INFORMATION_MODAL_PORT [defaults to 8080]



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
