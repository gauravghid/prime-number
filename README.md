Prime Number API
-------------------------
-------------------------

### Overview
Service is used to calculate and return all the prime numbers up to and including a number provided.
It takes the number as path variable and algorithm as query parameter to determine the list of prime numbers till the given range.
It uses BruteForce and Sieve algorithm(default value is Sieve Algorithm, if none of the algorithm is provided).
Service consists of following key parts:
* Supports multiple algorithm to generate list of  prime numbers.
* Basic authorization enabled
* Returns response(xml/JSON) based on the media type header provided (by default it returns response in xml)
* Swagger integration
* Aspect Oriented programming for Logging
* ProblemDetails class used for invalid input parameter and Exception handling.
* Caching results implemented
* Actuators(health,env,info,metrics)
  
### How to run-
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.
* Clone this repository - git@github.com:gauravghid/prime-number.git
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running mvn clean package
* Once successfully built, you can run the service by one of these two methods:

    java -jar target/prime-number-0.0.1-SNAPSHOT.jar

* Note: Default Server port  set in application.properties : 9092.

Once the application runs you should see something like this
2023-09-03T23:47:52.686+01:00  INFO 98531 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 9092 (http) with context path ''
2023-09-03T23:47:52.697+01:00  INFO 98531 --- [  restartedMain] c.e.prime.number.PrimeNumberApplication  : Started PrimeNumberApplication in 1.852 seconds (process running for 2.067)

### Technology Stack
* JDK 17
* SpringBoot framework 3.1

### Functional design
Code segregated by 6 main packages which represent main functional parts:
* **aspect** - to log message before and after execution of method.
* **configuration** - spring boot configuration for swagger, basic auth.
* **model** - class to hold DTO's.
* **controller** - classes that maintain REST API and handles exceptions.
* **service** - classes that contains business logic.
* **util** - contains algorithm enum values

### Validation and error handling
* Validation on input parameter and algorithm.

### Security
Microservice implements basic authorization.
Default credentials:
* username:user
* password:password

### API
Prime number service exposes only one REST API endpoint.
This API takes a number and an optional algorithm key and return list of prime numbers up to and including the number provided.
#### Algorithm Key
* Sieve Algorithm : SA
* Brute Force Algorithm : BFA

   If no algorithm key is provided, api generates prime numbers using Sieve Algorithm.
#### URI Details
* | URI | Method | Headers | URL Param | Response
* /api/v1.0/primes/{number}?algorithm={algorithmKey}| GET | Content-type= application/json,accept = application/json or application/xml | initials and list of prime numbers, 200 ok
* Eg :
    curl --location --request GET 'http://localhost:9092/api/v1.0/primes/7?algorithm=BFA' --header 'Accept: application/xml' --header 'Authorization: Basic dXNlcjpwYXNzd29yZA=='
* Response :
{
    "Initial": 14,
    "Primes": [
        2,
        3,
        5,
        7,
        11,
        13
    ]
}

Here are some endpoints you can call:

Get information about system health, configurations, etc.
* http://localhost:9092/actuator/env
* http://localhost:9092/actuator/health
* http://localhost:9092/actuator/info
* http://localhost:9092/actuator/metrics

### To view Swagger API docs
Run the server and browse to localhost:9092/swagger-ui/index.html

### Testing
The following testing approaches applied to ensure code stability and reliability
* unit tests
* integration tests
* local testing

### API Error specifications
Scopes:
* 400 - Bad request

Error response-
{
"type": "http://primes/errors/not-found",
"title": "Input provided is not valid!!",
"status": 400,
"detail": "Please provide valid input URI params",
"instance": "/api/v1.0/primes/t"
}


### Build & Deployment
Application contains DockerFile to generate image.
Microservice is deployed on GCP - URL to access.()
* https://prime-number-397923.uc.r.appspot.com/api/v1.0/primes/56?algorithm=BFA

### Add a new Algorithm
In order to add a new algorithm for prime number generation, please follow below steps :
1. Add a key and algorithm name in AlgorithmEnum
2. Add a new Service implementation for the new algorithm by implementing PrimeNumberService interface.

### Caching
This api caches the result of recently requested prime number using spring Cacheable features.
Caching is implemented at the service layer which caches the response returned from service layer.
