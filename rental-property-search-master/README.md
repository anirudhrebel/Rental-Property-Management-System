## Rental Property Spring Boot Application

# Quick Notes
- There were only 4 main requirements, so I did all of these even though you should not need
the docker-compose. And I built a RESTful API system instead of a CLI tool. Still finishing the same tasks though.

* Code must be in java, Java 8 preferred - Yes Java 1.8
* The database used must be an RDBMS, MySql/MariaDB strongly recommended - Yes in Mysql with hibernate and flyway
* You must provide a readme on how to build and make use of your tool - Yes your reading it
* Provide a docker-compose yaml that would instantiate your infrastructure - Yes but should not need

# Welcome Instructions

- First Off Instead of doing a CLI implementation I thought it to be much more worthwhile to
implement a RESTful API approach (since most of the project they need built will need many many API's) and to truly show my spring boot expertise as well as my java 8 experience
therefore this is a spring boot application implemeting all tasks that were asked for with a
small WORKING session. :) I hope you enjoy it as I step you through how to set it up and use it :)

# Setup Instructions

- First your going to need Java 8 and Maven installed. Its using Spring boot so its pretty easy to set up since its a maven project using a embedded tomcat server. Assuming who is reading this is a developer, your also going to need mysql installed, any version should work because i set up a flyway script that is going to build everything for you that you will need need.

- I did not use docker to build or test this application, its easy enough to just install mysql, maven and java 8 locally also the only service we would need in a docker-compose
would be mysql with a certain username and password

- After Mysql is installed please run this below in the command line...
sudo mysql --password
create database rental_property_app;
create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on rental_property_app.* to 'springuser'@'localhost';

- After that you should be all set just do a mvn clean spring-boot:run in the same directory as the pom and flyway with start building out the db structure when the application builds and flyways will also add a few items for you as "test data" :

# Running the Application
- Below is what you will use to run the application, please do that in the same directory as the POM.xml, if it successfully starts you will see
main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context

- mvn clean spring-boot:run

- After that the RESTful API is up and you should be able to hit it in a browser! Or you can go ahead and curl in the command line...

- Next step is crucial!! Before you curl anything please authenticate, i have created a user with a email bob@gmail.com...use the below curl or hit the route in your browser before using the rest of the application....

- Not sure who will be looking at this but if this project is built in docker then you will may need port 8087 instead?

//login to the api system
curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/login?email=bob@gmail.com

- Now that your authenticated have fun using the application by using curls or in the browser, im going to give you a few curls to try!!!

//view all of the rental properties
curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/viewall

//search for all properties with price between 1000 and 200000
curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/search?start=1000&end=200000

//save a property on your fav list by id
curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/savefavorite/{id}

//this will find the currently authenticated user and find their saved properties :)
curl -X GET -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/viewsaved

//this will add a new property
curl -X POST -d '{"propertyName":"main street harbor shack", "address":"8th main street", "city":"SouthVille", "price":"500938", "leaseTerm":"1"}' -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/saveproperty

//this will add a new user
curl -X POST -d '{"emailAddress":"henry@gmail.com"}' -H "content-type: application/json" -H "accept: application/json" http://localhost:8080/api/saveuser

# Ending Notes and Implementation

- Below is the script that flyway creates when your application first builds

- Initial DB Script
CREATE TABLE RENTAL_PROPERTY (
 ID int AUTO_INCREMENT primary key NOT NULL,
 ADDRESS varchar(50),
 CITY varchar(30),
 PRICE DOUBLE,
 LEASE_TERM DOUBLE,
 PROPERTY_NAME varchar(40)
);

CREATE TABLE SAVED_PROPERTY (
 ID int AUTO_INCREMENT primary key NOT NULL,
 RENTAL_ID int NOT NULL,
 USER_ID int NOT NULL
);

CREATE TABLE USER (
 ID int AUTO_INCREMENT primary key NOT NULL,
 EMAIL_ADDRESS varchar(50)
);

- add data
insert into USER values(1, "bob@gmail.com");
insert into rental_property values(1, "1 south street", "Grand Rapids", 250000,1,"Hickey Oaks Apartments");
insert into rental_property values(2, "2 south street", "Nashville TN", 25,2,"The Brown House");
insert into rental_property values(3, "4 north ave", "Ann Arbor MI", 2600,0.5,"Dirty Old Home");

- As you can see I handle a saved property by user id holding the user id and the property id that was saved in the SAVED_PROPERTY table. This will allow me todo everything i need to in order to save a favoriate property by user. Also If you look at the code in rentalpropertyrepository....I let hibernate do a between query for me to get all properties by price range.
