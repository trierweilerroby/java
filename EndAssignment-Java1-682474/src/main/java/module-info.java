module inholland.java.endassignment682474 {
    requires javafx.controls;
    requires javafx.fxml;


    opens inholland.java.endassignment682474 to javafx.fxml;
    exports inholland.java.endassignment682474;

    exports inholland.java.endassignment682474.Controller;
    opens inholland.java.endassignment682474.Controller to javafx.fxml;

    opens inholland.java.endassignment682474.model to javafx.fxml;
    exports inholland.java.endassignment682474.model;

}