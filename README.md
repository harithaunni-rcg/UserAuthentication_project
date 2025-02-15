# User Authentication Project

## Introduction
The User Authentication Project is a Spring Boot application that provides user authentication and authorization functionalities. It supports features such as user registration, login, role-based access control, and JWT authentication,swagger documentation and testing.

## Prerequisites
- Java 17
- Maven 3.8.1
- Docker 
- Swagger
- Mysql Database

## Installation

### Clone the Repository
```sh
cd user-authentication-project
git clone https://github.com/harithaunni-rcg/UserAuthentication_project.git

```
•Install mysql and create a schema with name userauthentication
•Update 'resources/application.properties' file with mysql connection details.
•update 'docker-compose.yml' file with mysql connection details, if required

## Accessing Swagger UI
```sh
http://localhost:8080/swagger-ui.html
