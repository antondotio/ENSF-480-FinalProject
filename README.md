# ENSF 480 Rental Management System
## Oscar Wong, Julian Pinto, Anton Santos
### Group 19

## How to Run the JARs
1. Run java -jar server.jar
2. Run java -jar client.jar

Login with a registered renter as oscar.wong1@ucalgary.ca, password is password
Landlord: julian.pinto@ucalgary.ca password
Manager: antonio.santos@ucalgary.ca password

Or you can sign up, but you can't sign up as a manager because that's an 'admin' type user that we manually create in the db.

## How to Run the Client-Server Application Manually
### Client
1. Make sure you have JavaFX and MySQL's JDBC Connector installed and included in your classpath.
2. Compile all classes in Views, class Main, and classes Client and Listener in Controller.
3. Run class Main with the following VM options: 
`--module-path="C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules="javafx.base,javafx.controls,javafx.fxml,javafx.graphics" `


### Server
1. Compile all classes in Controller except Client and Listener.
2. Compile all classes in Entity and Systems
3. Run class Server