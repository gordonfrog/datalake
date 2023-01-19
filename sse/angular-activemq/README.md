# JPA-Angular
School project involving the use of JPA, Spring Boot, Apache ActiveMQ, MySQL Server and the Angular Framework to simulate a car rental system.

# What is this?
Once you have downloaded this project, you'll have 2 distinct folders : one containing the Angular project and the the other containing two java projects, IST and Receiver (useful for JMS).

The main java project is IST, it contains all the models, repositories, controllers and MySQL database access properties.
Receiver is here just to illustrate how you can transfer data accross projects using message brokers.
IST-Angular-final contains the Angular app built to give the user a web interface using MDBootstrap.

# How to use?
First, go into "IST/src/main/resources/application.properties" to configure your database access.

Secondly, launch the ApacheMQ server by running the command 'bin\activemq start' in the parent directory of the bin folder.

Then, use the angular CLI the install the dependencies by using the 'npm install' command inside of the project directory.

After this, you can serve the application using the 'ng serve' command, it should build successfully.

Once this is done, you just have to navigate to this url : http://localhost:4200/ to use the Angular app.

Finally, run both IST and Receiver to make it fully work.
