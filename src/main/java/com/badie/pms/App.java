package com.badie.pms;


import com.badie.pms.controller.AdminLogin;
import com.badie.pms.db.MyConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        if (MyConnection.connection() != null) {
            stage.setWidth(950);
            stage.setHeight(590);
            stage.setMinWidth(950);
            stage.setMinHeight(590);
            new AdminLogin().showView(stage);
        }
    }
}
