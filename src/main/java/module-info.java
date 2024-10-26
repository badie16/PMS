module com.badie.pms {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.badie.pms to javafx.fxml;
    exports com.badie.pms;
    opens com.badie.pms.controller to javafx.fxml;
    exports com.badie.pms.controller;
}