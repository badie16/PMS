package com.badie.pms.controller;

import com.badie.pms.model.User;
import com.badie.pms.util.Directories;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryManagement implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<User,String> categoryId,categoryName;
    public TableView<User> tableCategory;

    public void onClickMenuNav(MouseEvent mouseEvent)  {
        ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User us1 = new User(1,"badie@g.com","zdd","admin");
        User us2 = new User(2,"jawad@g.com","zdd","admin");
        User us4 = new User(4,"said@g.com","zdd","admin");
        ObservableList<User> usersObs = FXCollections.observableArrayList();
        for (int i = 0; i < 25; i++) {
            usersObs.addAll(us1,us2,us4);
        }
        categoryId.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().user_id)));
        categoryName.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().user_email)));
//        .setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().user_pass)));
        tableCategory.setItems(usersObs);
    }

    public void showView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Directories.urlOfRsr(Directories.categoryManagementView));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
