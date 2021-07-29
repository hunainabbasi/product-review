# product-review

Note: I have tried to get it on dockers but due to lack of time I could'nt (though I have partially completed it but was able to connect my db inside docker)

## Architecture
- Java 11.0.2 --> Product Service
- Java 8 --> Review Service
- Maven (wrapper)
- Product Service : microservice responsible for retrieving product and review data
- Review Service : microservice responsible for managing review data
- MySql as database
- Swagger documentation available at /swagger-ui.html
- JUnit for unit testing

## Requirements
 - MySql
 - Java 11 (to run locally in IDE)
 - Unix based OS (Mac OS, Ubuntu, etc)



## Setup
 - First make sure that mysql service is up and running on : port 3306 and database is there named  review
 - Initially take clone and start all three application:
    - 1, Eureka Server that runs on port  : 8764 --> http://localhost:8761/
    - 2, Review Sevice that runs on port  : 8001
            - While starting review service iniial reviews will be populated as per data.sql file
            - i.e : Product_ID: 'M20324'
                  - Product_ID: 'BB5467'
                  - Product_ID: 'AD992'
        - Note:  Before starting review-serview kindly make sure to put username and password db respectively in application.properties file.
    - 3, Product Service that runs on port : 8002

 - At startup, database seed data is inserted into DB  ```Review``` table.

## Authorization
- Authorization has been implemented on post call whether you use it for save/update
   - username: root
   - pasword:  root
   - For post call
- However get calls are not authorize for review-service as well as product-service 

## Swagger
Swagger documentation available for both services (after docker startup)
 - Product service : http://localhost:8001/swagger-ui.html
 - Review service  : http://localhost:8002/swagger-ui.html

## Improvements to be done
- Enhanced security using API gateway for microservices communication using JWT and API Tokens;
- Implement docker including both services and mysql inside it;
- As Eureka is already implement we further use config server to move all configurations there so that we dont have to deploy services after config changes;
- Self management through technologies such as Kubernetes;
- Improve integration and unit tests with broader coverage
