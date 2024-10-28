package com.badie.pms.controller;

import com.badie.pms.db.UserDb;
import com.badie.pms.model.User;
import com.badie.pms.util.Directories;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLogin implements Initializable {
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
    public void onClickBtnAdmin(ActionEvent e) {
        if (pass.getText().isEmpty() || email.getText().isEmpty()) {
            Directories.alert("email or PassWord is empty ", Alert.AlertType.ERROR);
        }else {
            UserDb usDb = new UserDb();
            if(usDb.exitUser(email.getText(), pass.getText())){
                AdminDashboard dashboard = new AdminDashboard();
                Stage stage = Directories.stageFromEvent(e);
                try {
                    stage.hide();
                    AdminDashboard.adminId = usDb.getIdUserByEmail(email.getText());
                    dashboard.showView(stage);
                }catch (IOException ex){
                    System.out.println(ex);
                }
            }else {
                Directories.alert("Email or PassWord is incorrect ", Alert.AlertType.WARNING);
            }
        }
    }
    public void OnForgotPass(MouseEvent mouseEvent) {
        Directories.alert("Take a deep breath and relax, 😊" +
                " and you will remember the password 😏", Alert.AlertType.WARNING);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        email.setText("admin@gmail.com");
        pass.setText("admin");
    }
}
