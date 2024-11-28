package com.badie.pms.controller;

import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.model.ParkingDuration;
import com.badie.pms.util.Directories;
import com.badie.pms.util.GlobalFunction;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public TableColumn<ParkingDuration,String>  tabColDurationValue;
    public TableColumn<ParkingDuration,Integer> tabColDurationId;
    public TableColumn<ParkingDuration, String> tabColCreatedAt, tabColUpdateAt;
    public TableView<ParkingDuration> tableDuration;
    public TextField searchField;
    public PrgDurationDb durationDb;
    public List<ParkingDuration> listOfDuration;
    private boolean actionsAdded = false;
    /**
     * <h3>variable for Add</h3>
     * */
    public AnchorPane addParkingDurationContainer;
    public TextField addDurationField;
    ParkingDuration durationFocus;
    /**
     * <h3>variable for update</h3>
     * */
    public AnchorPane updateParkingDurationContainer;
    public TextField updateDurationField;
    @FXML
    public void goToDashboardPage(MouseEvent event) {
        ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
    }
    @FXML
    public void goToDurationPage(MouseEvent event) {
        toggleContainers(false,false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        durationDb = new PrgDurationDb();
        listOfDuration = durationDb.getListOfDuration();
        initializeTable(listOfDuration);
        toggleContainers(false, false);
    }

    public void initializeTable(List<ParkingDuration> listOfDurations) {
        ObservableList<ParkingDuration> durationObs = FXCollections.observableArrayList(listOfDurations);
        tabColDurationId.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getDuration_id()));
        tabColDurationValue.setCellValueFactory(e -> new SimpleStringProperty(e.getValue().getDuration_value() + " Hour"));
        tabColCreatedAt.setCellValueFactory(e -> new SimpleStringProperty(String.valueOf(e.getValue().getDuration_added_on())));
        tabColUpdateAt.setCellValueFactory(e -> new SimpleStringProperty(
                e.getValue().getDuration_updated_on() != null ? String.valueOf(e.getValue().getDuration_updated_on()) : "N/A"
        ));
        if (!actionsAdded) {
            TableColumn<ParkingDuration, Void> actionColumn = (TableColumn<ParkingDuration, Void>) getAcitionTableColumn();
            tableDuration.getColumns().add(actionColumn);
            actionsAdded = true;
        }
        tableDuration.setItems(durationObs);
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
    public void onAddDuration(ActionEvent actionEvent) {
        if (isNotValidateDurationField(addDurationField.getText())) return;
        boolean confirmation = Directories.alert("Add Parking Duration?", Alert.AlertType.CONFIRMATION);
        if (confirmation) {
            int durationValue = Integer.parseInt(addDurationField.getText());
            if (durationDb.exitParkingDurationValue(durationValue)) {
                Directories.alert("This duration already exists.", Alert.AlertType.INFORMATION);
            } else if (durationDb.addParkingDuration(String.valueOf(durationValue))) {
                Directories.alert("Parking duration added successfully.", Alert.AlertType.INFORMATION);
                initialize(null,null);
            } else {
                Directories.alert("Failed to add parking duration.", Alert.AlertType.ERROR);
            }
            addDurationField.clear();
        }
    }

    public void onUpdateDuration(ActionEvent actionEvent) {
        // Vérifier si un duration est sélectionné pour la mise à jour
        if (durationFocus == null) {
            Directories.alert("Please select a duration to update.", Alert.AlertType.ERROR);
            initialize(null, null);
            return;
        }
        // Vérifier si tous les champs sont valides
        String updatedValue = updateDurationField.getText();
        if (isNotValidateDurationField(updatedValue)) return;
        // Convertir la nouvelle valeur en entier
        int newDurationValue = Integer.parseInt(updatedValue);
        // Vérifier si la nouvelle valeur existe déjà
        if (durationDb.exitParkingDurationValue(newDurationValue)) {
            Directories.alert("This duration already exists. Please choose a different value.", Alert.AlertType.INFORMATION);
            return;
        }
        // Mettre à jour duration dans la base de données
        boolean isUpdated = durationDb.updateParkingDuration(durationFocus.getDuration_id(), newDurationValue);
        if (isUpdated) {
            Directories.alert("Parking duration updated successfully.", Alert.AlertType.INFORMATION);
            initialize(null, null); // Réinitialiser la vue
        } else {
            Directories.alert("Failed to update parking duration. Please try again.", Alert.AlertType.ERROR);
        }
        // Réinitialiser le champ de mise à jour
        updateDurationField.clear();
    }

    public void onDeleteDuration(ParkingDuration durationFocus) {
        try {
            // Vérifiez si l'utilisateur souhaite supprimer la durée
            boolean confirm = Directories.alert(
                    "Are you sure you want to delete this duration (" + durationFocus.getDuration_value() + " hours)?",
                    Alert.AlertType.CONFIRMATION
            );
            if (confirm) {
                boolean isDeleted = new PrgDurationDb().deleteParkingDuration(durationFocus.getDuration_id());
                if (isDeleted) {
                    Directories.alert("Duration deleted successfully.", Alert.AlertType.INFORMATION);
                    initialize(null, null); // Réinitialiser la vue
                } else {
                    Directories.alert("Failed to delete duration. It might be linked to other data.", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            Directories.alert("An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void onShowAddPage(ActionEvent actionEvent) {
        try {
            // Show the add parking duration container
            toggleContainers(true,false);
        } catch (Exception e) {
            // Display an error message if initialization fails
            Directories.alert("An error occurred while opening the add page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void onShowUpdatePage(ParkingDuration duration) {
        try {
            updateDurationField.setText(String.valueOf(duration.getDuration_value()));
            durationFocus = duration; // Store the selected duration
            toggleContainers(false, true);
        } catch (Exception e) {
            Directories.alert("Error while preparing update view: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @Override
    public void onClickActionDelete(Object item) {
        onDeleteDuration((ParkingDuration) item);
    }
    @Override
    public void onClickActionUpdate(Object item) {
        onShowUpdatePage((ParkingDuration) item);
    }
    private void toggleContainers(boolean showAdd, boolean showUpdate) {
        addParkingDurationContainer.setVisible(showAdd);
        updateParkingDurationContainer.setVisible(showUpdate);
    }
    private boolean isNotValidateDurationField(String input) {
        if (input.isEmpty()) {
            Directories.alert("Duration field cannot be empty.", Alert.AlertType.ERROR);
            return true;
        }
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Directories.alert("Please enter a valid numeric value for duration.", Alert.AlertType.ERROR);
            return true;
        }
        return false;
    }

}
