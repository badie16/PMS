module com.badie.pms {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;


    opens com.badie.pms to javafx.fxml;
    exports com.badie.pms;
    opens com.badie.pms.controller to javafx.fxml;
    exports com.badie.pms.controller;
    opens com.badie.pms.model to javafx.fxml;
    exports com.badie.pms.model;
}