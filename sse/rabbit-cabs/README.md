# Angular8-Spring-boot-RabbitMq-Cabs-Booking-System

It's a simple application which is used to manage cabs bookings. It's divided into two parts

1. Front end interface(GUI) which allow user to create a booking, update, delete and list bookings. It consume services exposed by the back end service.
2. Backend service which expose rest api for CRUD operation. Also it expose api which can be used to create,update and delete bookings asynchronously. Asynchronous part is achieved by making use of Rabbit MQ.

 Server-side technologies

    Spring Boot - 2.2.4.RELEASE
    JDK - 1.8 or later
    Rabbit MQ
    Spring Data JPA - 2+
    maven
    
 Front end technologies

    Angular cli 8.3.23
    Node 13.0.7
    Bootstrap 4
    npm- 6.13.7
    JQuery

How to run and test

 **Spring boot application: cabsbooking**

- Navigate to cabsbooking folder e.g cd cabsbooking
- Clean and build - mvn clean compile 
- mvn spring-boot: run 
- Alternatively you can package the file and run the jar file as follows;
     1. mvn package 
     2. java -jar target/booking-0.0.1.jar
     
 **Angular application : cabs-booking-app**      
  - Navigate to cabs-booking folder 
  - npm install
  - Open command prompt and type : ng serve --open
  

