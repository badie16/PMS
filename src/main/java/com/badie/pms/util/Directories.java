package com.badie.pms.util;
import com.badie.pms.App;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;

public interface Directories {
    String adminLoginView = "view/AdminLogin.fxml";
    String adminDashboardView = "view/AdminDashboard.fxml";
    String adminProfileView = "view/AdminProfile.fxml";
    String categoryManagementView = "view/CategoryManagement.fxml";

    static URL urlOfRsr(String str){
        return App.class.getResource(str);
    }

    static Stage stageFromEvent(ActionEvent e){
        return  (Stage) (((Node)e.getSource()).getScene().getWindow());
    }

    static boolean alert(String text, Alert.AlertType type){
        Alert alert;
        alert = new Alert(type);
        alert.setTitle(type.toString());
        alert.setHeaderText(null);
        alert.setContentText(text);
        Optional<ButtonType> option = alert.showAndWait();
        return option.isPresent() && option.get().equals(ButtonType.OK);
    }
}
