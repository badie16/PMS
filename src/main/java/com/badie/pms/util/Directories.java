package com.badie.pms.util;
import com.badie.pms.App;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.Optional;

public interface Directories {
    String adminLoginView = "view/AdminLogin.fxml";
    static URL urlOfRsr(String str){
        return App.class.getResource(str);
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
