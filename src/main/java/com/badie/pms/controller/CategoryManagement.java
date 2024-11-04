package com.badie.pms.controller;

import com.badie.pms.db.PrgCategoryDb;
import com.badie.pms.model.ParkingCategory;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagement implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<ParkingCategory,String> categoryId,categoryName;
    public TableColumn<ParkingCategory, String> categoryCreatedAt,categoryUpdateAt;
    public TableView<ParkingCategory> tableCategory;
    public List<ParkingCategory> CategoryList;

    public void onClickMenuNav(MouseEvent mouseEvent)  {
        ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PrgCategoryDb prgCategoryDb = new PrgCategoryDb();
        ObservableList<ParkingCategory> usersObs = FXCollections.observableArrayList(prgCategoryDb.getListOfCategory());
        categoryId.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_id())));
        categoryName.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_name())));
        categoryCreatedAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_added_on())));
        categoryUpdateAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(
                (e.getValue().getCategory_updated_on() != null)? e.getValue().getCategory_updated_on():"N/A"
        )));
        tableCategory.setItems(usersObs);
    }

    public void showView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Directories.urlOfRsr(Directories.categoryManagementView));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
