package com.badie.pms.controller;

import com.badie.pms.db.VehCategoryDb;
import com.badie.pms.model.VehicleCategory;
import com.badie.pms.util.Directories;
import com.badie.pms.util.GlobalFunction;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagement extends GlobalFunction implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<VehicleCategory,String> categoryId,categoryName;
    public TableColumn<VehicleCategory, String> categoryCreatedAt,categoryUpdateAt;
    public TableView<VehicleCategory> tableCategory;
    public TextField searchField;
    public VehCategoryDb vehCategoryDb;
    public List<VehicleCategory> listOfCategory;
    public Label toDashboardPageBtn1, toCategoryPageBtn, toDashboardPageBtn2;
    public AnchorPane addVehicleCategoryContainer,manageVehicleCategoryContainer;
    public TextField addCategoryField;

    public void onClickMenuNav(MouseEvent mouseEvent)  {
        if (mouseEvent.getSource() == toDashboardPageBtn1 || mouseEvent.getSource() == toDashboardPageBtn2){
            ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
        }else if(mouseEvent.getSource() == toCategoryPageBtn){
            addVehicleCategoryContainer.setVisible(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vehCategoryDb = new VehCategoryDb();
        listOfCategory = vehCategoryDb.getListOfCategory();
        initializeTable(listOfCategory);
        addVehicleCategoryContainer.setVisible(false);
    }
    public void initializeTable(List<VehicleCategory> listOfCategory){
        ObservableList<VehicleCategory> usersObs = FXCollections.observableArrayList(listOfCategory);
        categoryId.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_id())));
        categoryName.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_name())));
        categoryCreatedAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getCategory_added_on())));
        categoryUpdateAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(
                (e.getValue().getCategory_updated_on() != null)? e.getValue().getCategory_updated_on():"N/A"
        )));
        tableCategory.setItems(usersObs);
    }
    public void onSearchCategory(KeyEvent keyEvent) {
        if (keyEvent.getSource() == searchField){
            String value = searchField.getText().toLowerCase();
            if (!value.isEmpty()){
                ObservableList<VehicleCategory> usersObs = FXCollections.observableArrayList(listOfCategory);
                FilteredList<VehicleCategory> filteredList = new FilteredList<>(usersObs, p->true);
                filteredList.setPredicate(item -> {
                    return String.valueOf(item.getCategory_id()).contains(value) || item.getCategory_name().toLowerCase().contains(value);
                });
                initializeTable(filteredList);
            }else {
                initializeTable(listOfCategory);
            }
        }
    }

    public void addVehicleCategory(ActionEvent actionEvent) {
        if (addCategoryField.getText().isEmpty()){
            Directories.alert("Please fill the name field",
                    Alert.AlertType.ERROR);
        }else {
            boolean choix = Directories.alert("Add Vehicle Category ?", Alert.AlertType.CONFIRMATION);
            if (choix){
                if (vehCategoryDb.addVehicleCategory(addCategoryField.getText())){
                    Directories.alert("Vehicle Category has successfully added",
                            Alert.AlertType.INFORMATION);
                    initialize(null,null);
                }else {
                    Directories.alert("Vehicle Category has not successfully added",
                            Alert.AlertType.ERROR);
                }
            }
        }
    }

    public void onShowAddCategoryPage(ActionEvent actionEvent) {
        addVehicleCategoryContainer.setVisible(true);
    }

    @Override
    public void onClickActionDelete(Object item) {

    }

    @Override
    public void onClickActionUpdate(Object item) {

    }
}
