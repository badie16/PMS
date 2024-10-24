module com.badie.pms {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens com.badie.pms to javafx.fxml;
    exports com.badie.pms;
}