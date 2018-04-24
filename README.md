# Spring Cloud Client For Aws Lambda
===========================================

This is an out of the box implementation of a Spring Cloud Function application that connects to a Cloud Config Server to load the configuration for the aplication.

The purpose of this project is to show a way to work with spring and AWS Lambda.

This project provides use the to the [Cloud Config Server](https://stash1-tools.swacorp.com/users/x233108/repos/cloudconfigproject/browse) to get the properties.

To work with AWS Lambda you shoul upload the jar and set the environment varaiables to define the spring profile. the Handler method that you must define in the AWS lambda function is: app.handlers.EventRequestHandler

Technologies
------------

- Spring Boot 1.5.10.RELEASE
- Spring Cloud Function 1.0.0.BUILD-SNAPSHOT
- Aws Lambda Core Version
- Aws Lambda Events Version
- bmuschko/gradle-docker-plugin


How To Compile
--------------

The service can be compiled with:

```
gradle clean build
```

How To Generate Docker Image
--------------

```
gradle buildImage
```
```
mvn package
```


How To Run
----------

The service can be started with:

```
gradle bootRun
```
You may need to change the application YML to point to the bitbucker repo.



How To Test
----------

The service can be tested with:

```
gradle test
```


Remarks on the Code
-------------------

This ia a spring boot application integrated with AWS Lambda using the SpringBootRequestHandler, depending on the profile that you define in the AWS lambda environment variables the application start one proccesosr:
```
|Environment variable (KEY)  | Environment variable  (VALUE) |       Processor      |
|spring_profiles_active      | flight_profile                | FlightEventProcessor |
|spring_profiles_active      | crew_profile                  | CrewEventProcessor   |
```
To specify the configuration that you want to load for the application you must specify the name of the application and the name of the profile:
```
|Environment variable (KEY)  | Environment variable  (VALUE)   |
|spring_application_name     | event_consumer                  |
|spring_profiles_active      | prod_plt_flight, flight_profile |
```
This way you will load the event_consumer-prod_plt_flight.yml configuration file and the application weill start the FlightEventProcessor bean.

To test the application you shoul sen the request defined in the class app.domain.event.EventMessage with a json format:
```
{
  "eventId": "123",
  "message": "request test"
}
```
and if everithing is ok you sould see the response with the format defined in the class: class app.domain.response.EventResponse
```
{
  "eventId": "123",
  "message": "EventRequest processed by: flight_profile",
  "requestSize": 12
}
```
