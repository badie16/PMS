package com.badie.pms.controller;
import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.db.PrgPriceDb;
import com.badie.pms.db.VehCategoryDb;
import com.badie.pms.model.ParkingDuration;
import com.badie.pms.model.ParkingPrice;
import com.badie.pms.model.VehicleCategory;
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

public class ParkingPricesManagement implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<ParkingPrice,String> TabColId, TabColCategory,TabColDuration,TabColPrice;
    public TableColumn<ParkingPrice, String> TabColCreatedAt, TabColUpdateAt;
    public TableView<ParkingPrice> tablePrices;
    public TextField searchField;
    public PrgPriceDb priceDb;
    public List<ParkingPrice> listOfPrices;
    public Label toDashboardPageBtn1, toPricesPageBtn, toDashboardPageBtn2;
    public AnchorPane addParkingPricesContainer;
    public TextField addDurationField;
    public ComboBox<String> categoryChoixBox,durationChoixBox;
    public TextField PriceValueField;

    public void onClickMenuNav(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == toDashboardPageBtn1 || mouseEvent.getSource() == toDashboardPageBtn2){
            ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
            initializeCategoryChoixBox(categoryChoixBox,new VehCategoryDb().getListOfCategory());
            initializeDurationChoixBox(durationChoixBox,new PrgDurationDb().getListOfDuration());
        }else if(mouseEvent.getSource() == toPricesPageBtn){
            addParkingPricesContainer.setVisible(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceDb = new PrgPriceDb();
        listOfPrices = priceDb.getListOfPrices();
        initializeTable(listOfPrices);
        addParkingPricesContainer.setVisible(false);
    }
    public void initializeTable(List<ParkingPrice> listOfPrices){
        ObservableList<ParkingPrice> usersObs = FXCollections.observableArrayList(listOfPrices);
        TabColId.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getPrice_id())));
        TabColPrice.setCellValueFactory(e->new SimpleStringProperty((String.valueOf( e.getValue().getPrice_value()))));
        TabColDuration.setCellValueFactory(e -> new SimpleStringProperty(
                e.getValue().getDuration_value() + " Hour"
                )
        );
        TabColCategory.setCellValueFactory(e -> new SimpleStringProperty(
                e.getValue().getCategory_name()
                )
        );
        TabColCreatedAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getPrice_added_on())));
        TabColUpdateAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(
                (e.getValue().getPrice_updated_on() != null)? e.getValue().getPrice_updated_on():"N/A"
        )));
        tablePrices.setItems(usersObs);
    }
    public void initializeDurationChoixBox(ComboBox<String> comboBox,List<ParkingDuration> list){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (ParkingDuration pr : list) {
            observableList.add(String.valueOf(pr.getDuration_value()));
        }
        comboBox.setItems(observableList);
    }
    public void initializeCategoryChoixBox(ComboBox<String> comboBox,List<VehicleCategory> list){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (VehicleCategory pr : list) {
            System.out.println(observableList);
            observableList.add(pr.getCategory_name());
        }
        comboBox.setItems(observableList);
        System.out.println(comboBox.getItems());
    }
    public void onSearchDuration(KeyEvent keyEvent) {
        if (keyEvent.getSource() == searchField){
            String value = searchField.getText().toLowerCase();
            if (!value.isEmpty()){
                ObservableList<ParkingPrice> usersObs = FXCollections.observableArrayList(listOfPrices);
                FilteredList<ParkingPrice> filteredList = new FilteredList<>(usersObs, p->true);
                filteredList.setPredicate(item -> {
                    return String.valueOf(item.getPrice_id()).contains(value) || String.valueOf(item.getPrice_value()).contains(value)
                            || item.getCategory_name().toLowerCase().contains(value) || String.valueOf(item.getDuration_value()).contains(value);
                });
                initializeTable(filteredList);
            }else {
                initializeTable(listOfPrices);
            }
        }
    }
    
    public void addParkingPrices(ActionEvent actionEvent) {
//        if (addDurationField.getText().isEmpty()){
//            Directories.alert("Please fill the name field",
//                    Alert.AlertType.ERROR);
//        }else {
//            boolean choix = Directories.alert("Add Parking Duration ?", Alert.AlertType.CONFIRMATION);
//            if (choix){
//                if (priceDb.exitParkingDurationValue(Integer.parseInt(addDurationField.getText()))){
//                    Directories.alert("This Duration ",
//                            Alert.AlertType.INFORMATION);
//                    addDurationField.setText("");
//                }else {
//                    if (priceDb.addParkingDuration(addDurationField.getText())) {
//                        Directories.alert("Parking Duration has successfully added",
//                                Alert.AlertType.INFORMATION);
//                        addDurationField.setText("");
//                        initialize(null, null);
//                    } else {
//                        Directories.alert("Parking Duration has not successfully added",
//                                Alert.AlertType.ERROR);
//                    }
//                }
//            }
//        }

    }
    public void onShowAddPage(ActionEvent actionEvent) {
        addParkingPricesContainer.setVisible(true);
    }

}
