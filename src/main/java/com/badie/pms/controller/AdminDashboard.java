package com.badie.pms.controller;

import com.badie.pms.db.UserDb;
import com.badie.pms.model.User;
import com.badie.pms.util.Directories;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    public static int adminId;
    public AnchorPane menu;
    public Button btnProfilePage;
    public BorderPane mainAppContainer;
    public AnchorPane dashboardView;
    public static AnchorPane dashboardViewStatic;
    public Button btnCategoryPage,btnDurationPage;
    private User admin;
    public AdminDashboard(){

    }
    public void showView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Directories.urlOfRsr(Directories.adminDashboardView));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
    public void onToggleMenu(MouseEvent mouseEvent){
          menu.setPrefWidth(menu.getPrefWidth() == 0 ? 220 : 0);
    }
    public void onClickMenuNav(ActionEvent event) throws IOException {
        if (event.getSource().equals(btnProfilePage)) {
            FXMLLoader fx = new FXMLLoader(Directories.urlOfRsr(Directories.adminProfileView));
            Parent root = fx.load();
            AdminProfile controller = fx.getController();
            controller.setAdmin(admin);
            mainAppContainer.setCenter(root);
        } else if (event.getSource().equals(btnCategoryPage)) {
            FXMLLoader fx = new FXMLLoader(Directories.urlOfRsr(Directories.categoryManagementView));
            Parent root = fx.load();
            mainAppContainer.setCenter(root);
        } else if (event.getSource().equals(btnDurationPage)) {
            FXMLLoader fx = new FXMLLoader(Directories.urlOfRsr(Directories.parkingDurationManagementView));
            Parent root = fx.load();
            mainAppContainer.setCenter(root);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardViewStatic = dashboardView;
        UserDb usDb = new UserDb();
        this.setAdmin(usDb.getUser(adminId));

    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
