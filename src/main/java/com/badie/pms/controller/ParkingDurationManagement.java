package com.badie.pms.controller;

import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.model.ParkingDuration;
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

public class ParkingDurationManagement extends GlobalFunction implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<ParkingDuration,String> durationId,durationValue;
    public TableColumn<ParkingDuration, String> durationCreatedAt,durationUpdateAt;
    public TableView<ParkingDuration> tableDuration;
    public TextField searchField;
    public PrgDurationDb durationDb;
    public List<ParkingDuration> listOfDuration;
    public Label toDashboardPageBtn1, toDurationPageBtn, toDashboardPageBtn2;
    public AnchorPane addParkingDurationContainer;
    public TextField addDurationField;


    public void onClickMenuNav(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == toDashboardPageBtn1 || mouseEvent.getSource() == toDashboardPageBtn2){
            ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
        }else if(mouseEvent.getSource() == toDurationPageBtn){
            addParkingDurationContainer.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        durationDb = new PrgDurationDb();
        listOfDuration = durationDb.getListOfDuration();
        initializeTable(listOfDuration);
        addParkingDurationContainer.setVisible(false);
    }

    public void initializeTable(List<ParkingDuration> listOfCategory){
        ObservableList<ParkingDuration> usersObs = FXCollections.observableArrayList(listOfCategory);
        durationId.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDuration_id())));
        durationValue.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDuration_value()) + " Hour"));
        durationCreatedAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDuration_added_on())));
        durationUpdateAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(
                (e.getValue().getDuration_updated_on() != null)? e.getValue().getDuration_updated_on():"N/A"
        )));
        tableDuration.setItems(usersObs);
    }

    public void onSearchDuration(KeyEvent keyEvent) {
        if (keyEvent.getSource() == searchField){
            String value = searchField.getText().toLowerCase();
            if (!value.isEmpty()){
                ObservableList<ParkingDuration> usersObs = FXCollections.observableArrayList(listOfDuration);
                FilteredList<ParkingDuration> filteredList = new FilteredList<>(usersObs, p->true);
                filteredList.setPredicate(item -> {
                    return String.valueOf(item.getDuration_id()).contains(value) || String.valueOf(item.getDuration_value()).contains(value);
                });
                initializeTable(filteredList);
            }else {
                initializeTable(listOfDuration);
            }
        }
    }

    public void addParkingDuration(ActionEvent actionEvent) {
        if (addDurationField.getText().isEmpty()){
            Directories.alert("Please fill the name field",
                    Alert.AlertType.ERROR);
        }else {
            boolean choix = Directories.alert("Add Parking Duration ?", Alert.AlertType.CONFIRMATION);
            if (choix){
                if (durationDb.exitParkingDurationValue(Integer.parseInt(addDurationField.getText()))){
                    Directories.alert("This Duration ",
                            Alert.AlertType.INFORMATION);
                    addDurationField.setText("");
                }else {
                    if (durationDb.addParkingDuration(addDurationField.getText())) {
                        Directories.alert("Parking Duration has successfully added",
                                Alert.AlertType.INFORMATION);
                        addDurationField.setText("");
                        initialize(null, null);
                    } else {
                        Directories.alert("Parking Duration has not successfully added",
                                Alert.AlertType.ERROR);
                    }
                }
            }
        }

    }
    public void onShowAddDurationPage(ActionEvent actionEvent) {
        addParkingDurationContainer.setVisible(true);
    }



}
