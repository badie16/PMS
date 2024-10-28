package com.badie.pms.controller;

import com.badie.pms.db.UserDb;
import com.badie.pms.model.User;
import com.badie.pms.util.Directories;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminProfile {
    public AnchorPane mainContainer;
    public TextField emailInput;
    public PasswordField passInput;
    private User admin = new User();
    public void onClickMenuNav(MouseEvent mouseEvent) throws IOException {
        ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
    }

    private boolean isInfoInputEmpty() {
        return passInput.getText().isEmpty() || emailInput.getText().isEmpty();
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    /* start admin info edit */

    public void updateStudent(){
        if (isInfoInputEmpty() ){
            Directories.alert("Please fill all blank fields",
                    Alert.AlertType.ERROR);
        }else {
            boolean choix = Directories.alert("Update Student ?", Alert.AlertType.CONFIRMATION);
            if (choix) {
                admin.user_email = emailInput.getText();
                admin.user_pass = emailInput.getText();
                UserDb usDb = new UserDb();
                usDb.updateUser(admin);
                Directories.alert("Student has successfully update",
                        Alert.AlertType.INFORMATION);
            }
        }
    }

}
