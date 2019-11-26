# ENSF 480 Rental Management System
## Oscar Wong, Julian Pinto, Anton Santos
### Group 19

## How to Run the Client-Server Application
### Client
1. Make sure you have JavaFX and MySQL's JDBC Connector installed and included in your classpath.
2. Compile all classes in Views, class Main, and classes Client and Listener in Controller.
3. Run class Main with the following VM options: 
`--module-path="C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules="javafx.base,javafx.controls,javafx.fxml,javafx.graphics" `

### Server
1. Compile all classes in Controller except Client and Listener.
2. Compile all classes in Entity and Systems
3. Run class Server