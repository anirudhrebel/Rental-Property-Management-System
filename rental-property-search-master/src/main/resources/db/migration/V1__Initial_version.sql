
--Initial DB Script
CREATE TABLE rental_property (
 ID int NOT NULL AUTO_INCREMENT primary key NOT NULL,
 ADDRESS varchar(50),
 CITY varchar(30),
 PRICE DOUBLE,
 LEASE_TERM DOUBLE,
 PROPERTY_NAME varchar(40)
);

CREATE TABLE saved_property (
 ID int NOT NULL AUTO_INCREMENT primary key NOT NULL,
 RENTAL_ID int NOT NULL,
 USER_ID int NOT NULL
);

CREATE TABLE user (
 ID int NOT NULL AUTO_INCREMENT primary key NOT NULL,
 EMAIL_ADDRESS varchar(50)
);

--add data
insert into USER values(1, "bob@gmail.com");
insert into rental_property values(1, "1 south street", "Grand Rapids", 250000,1,"Hickey Oaks Apartments");
insert into rental_property values(2, "2 south street", "Nashville TN", 25,2,"The Brown House");
insert into rental_property values(3, "4 north ave", "Ann Arbor MI", 2600,0.5,"Dirty Old Home");