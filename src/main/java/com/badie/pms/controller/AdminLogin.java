package com.badie.pms.controller;

import com.badie.pms.db.UserDb;
import com.badie.pms.util.Directories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
public class AdminLogin {
    @FXML
    TextField email;
    @FXML
    PasswordField pass;
    public AdminLogin(){

    }
    public void showView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Directories.urlOfRsr(Directories.adminLoginView));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }
    public void onClickBtnAdmin(ActionEvent actionEvent) {
        if (pass.getText().isEmpty() || email.getText().isEmpty()) {
            Directories.alert("email or PassWord is empty ", Alert.AlertType.ERROR);
        }else {
            UserDb usDb = new UserDb();
            System.out.println(usDb.exitUser(email.getText(), pass.getText()));
        }
    }
    public void OnForgotPass(MouseEvent mouseEvent) {
        Directories.alert("Take a deep breath and relax, 😊" +
                " and you will remember the password 😏", Alert.AlertType.WARNING);
    }
}
