package com.badie.pms;

import com.badie.pms.controller.AdminDashboard;
import com.badie.pms.controller.AdminLogin;
import com.badie.pms.db.MyConnection;
import com.badie.pms.util.Directories;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {
        if (MyConnection.connection() != null) {
            new AdminLogin().showView(stage);
        }
    }
}
