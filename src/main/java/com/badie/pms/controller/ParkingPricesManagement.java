package com.badie.pms.controller;
import com.badie.pms.db.PrgDurationDb;
import com.badie.pms.db.PrgPriceDb;
import com.badie.pms.db.VehCategoryDb;
import com.badie.pms.model.ParkingDuration;
import com.badie.pms.model.ParkingPrice;
import com.badie.pms.model.VehicleCategory;
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
import java.util.function.Function;

public class ParkingPricesManagement extends GlobalFunction implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<ParkingPrice,String> TabColCategory,TabColDuration;
    public TableColumn<ParkingPrice,Integer> TabColId;
    public TableColumn<ParkingPrice,Double> TabColPrice;
    public TableColumn<ParkingPrice, String> TabColCreatedAt, TabColUpdateAt;
    public TableView<ParkingPrice> tablePrices;
    public TextField searchField;
    public PrgPriceDb priceDb;
    public List<ParkingPrice> listOfPrices;
    /**
     * <h3>variable for Add</h3>
     * */
    public AnchorPane addParkingPricesContainer;
    public ComboBox<String> categoryChoixBox,durationChoixBox;
    public TextField PriceValueField;
    /**
    * <h3>variable for update</h3>
    * */
    public ParkingPrice priceFocus;
    public ComboBox<String> categoryChoixBoxForUpdate,durationChoixBoxForUpdate;
    public TextField priceValueFieldForUpdate;
    public AnchorPane updateParkingPricesContainer;
    private boolean actionsAdded = false;
    @FXML
    public void goToDashboardPage(MouseEvent event) {
        ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
    }
    @FXML
    public void goToPricesPage(MouseEvent event) {
        toggleContainers(false,false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priceDb = new PrgPriceDb();
        listOfPrices = priceDb.getListOfPrices();
        initializeTable(listOfPrices);
        toggleContainers(false,false);
    }
    public void initializeTable(List<ParkingPrice> listOfPrices){
        ObservableList<ParkingPrice> usersObs = FXCollections.observableArrayList(listOfPrices);
        TabColId.setCellValueFactory(e -> new SimpleObjectProperty<>(e.getValue().getPrice_id()));
        TabColPrice.setCellValueFactory(e->new SimpleObjectProperty<>( e.getValue().getPrice_value()));
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
        if (!actionsAdded) {
            TableColumn<ParkingPrice, Void> actionColumn = (TableColumn<ParkingPrice, Void>)getAcitionTableColumn();
            tablePrices.getColumns().add(actionColumn);
            actionsAdded = true;
        }
        tablePrices.setItems(usersObs);
    }
    public void initializeComboBox(ComboBox<String> comboBox, List<?> list, Function<Object, String> mapper) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        list.forEach(item -> observableList.add(mapper.apply(item)));
        comboBox.setItems(observableList);
    }

    @Override
    public void onClickActionDelete(Object item) {
        onDeletePrice((ParkingPrice) item);
    }
    @Override
    public void onClickActionUpdate(Object item) {
        onShowUpdatePage((ParkingPrice) item);
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

    public void onAddPrices(ActionEvent actionEvent) {
        try {
            // Vérifier si tous les champs sont valides
            if (isNotValidateFields(categoryChoixBox, durationChoixBox, PriceValueField)) {
                return;
            }
            // Récupérer les valeurs des champs
            String selectedCategory = categoryChoixBox.getSelectionModel().getSelectedItem();
            String selectedDuration = durationChoixBox.getSelectionModel().getSelectedItem().replace("Hour", "").trim();
            double priceValue = Double.parseDouble(PriceValueField.getText());
            // Récupérer les IDs correspondants
            int categoryId = new VehCategoryDb().getCategoryIdByName(selectedCategory);
            int durationId = new PrgDurationDb().getDurationIdByValue(Integer.parseInt(selectedDuration));

            // Vérifier si une entrée similaire existe déjà
            if (priceDb.exitParkingPriceValue(categoryId, durationId)) {
                Directories.alert("Price data for the selected category and duration already exists in the database.", Alert.AlertType.ERROR);
                return;
            }

            // Ajouter le prix dans la base de données
            boolean isAdded = priceDb.addParkingPrice(categoryId, durationId, priceValue);
            if (isAdded) {
                Directories.alert("Parking price has been successfully added.", Alert.AlertType.INFORMATION);
                // Réinitialiser les champs
                PriceValueField.clear();
                categoryChoixBox.getSelectionModel().clearSelection();
                durationChoixBox.getSelectionModel().clearSelection();

                // Réinitialiser la vue
                initialize(null, null);
            } else {
                Directories.alert("Failed to add parking price.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            Directories.alert("An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void onUpdatePrice(ActionEvent actionEvent) {
            // Vérifier si un prix est sélectionné pour la mise à jour
            if (priceFocus == null) {
                Directories.alert("Please select a price to update.", Alert.AlertType.ERROR);
                initialize(null, null);
                return;
            }
            // Vérifier si tous les champs sont valides
            if (isNotValidateFields(categoryChoixBoxForUpdate, durationChoixBoxForUpdate, priceValueFieldForUpdate)) {
                return;
            }
            // Récupérer les valeurs des champs
            String selectedCategory = categoryChoixBoxForUpdate.getSelectionModel().getSelectedItem();
            String selectedDuration = durationChoixBoxForUpdate.getSelectionModel().getSelectedItem().replace("Hour", "").trim();
            double priceValue = Double.parseDouble(priceValueFieldForUpdate.getText());
            System.out.println(selectedCategory+" "+selectedDuration+" "+priceValue);
            // Récupérer les IDs correspondants
            int categoryId = new VehCategoryDb().getCategoryIdByName(selectedCategory);
            int durationId = new PrgDurationDb().getDurationIdByValue(Integer.parseInt(selectedDuration));

            // Vérifier si une entrée similaire existe déjà (autre que l'entrée actuelle)
            if (priceDb.exitParkingPriceValue(categoryId, durationId) &&
                    !(priceFocus.getCategory_id() == categoryId && priceFocus.getDuration_id() == durationId)) {
                Directories.alert("Price data for the selected category and duration already exists in the database.", Alert.AlertType.ERROR);
                return;
            }

            // Mettre à jour le prix dans la base de données
            boolean isUpdated = priceDb.updateParkingPrice(priceFocus.getPrice_id(), categoryId, durationId, priceValue);
            if (isUpdated) {
                Directories.alert("Parking price has been successfully updated.", Alert.AlertType.INFORMATION);

                // Réinitialiser les champs et la vue
                PriceValueField.clear();
                categoryChoixBox.getSelectionModel().clearSelection();
                durationChoixBox.getSelectionModel().clearSelection();
                toggleContainers(true,false);
                initialize(null, null);
            } else {
                Directories.alert("Failed to update parking price.", Alert.AlertType.ERROR);
            }

    }
    public void onDeletePrice(ParkingPrice priceFocus) {
        try {
            // Assigner l'élément sélectionné
            if (priceFocus == null) {
                Directories.alert("Please select a price to update.", Alert.AlertType.WARNING);
                return;
            }
            // Afficher une alerte de confirmation
            boolean choix = Directories.alert(
                    "Are you sure you want to delete the price for " +
                            priceFocus.getCategory_name() + " (" + priceFocus.getDuration_value() + " Hour)?",
                    Alert.AlertType.CONFIRMATION
            );
            if (choix) {
                // Tenter de supprimer le prix
                boolean isDeleted = priceDb.deleteParkingPrice(priceFocus.getPrice_id());
                if (isDeleted) {
                    // Succès
                    Directories.alert("Price data has been successfully removed.", Alert.AlertType.INFORMATION);
                } else {
                    // Échec
                    Directories.alert("Failed to remove the price data. Please try again.", Alert.AlertType.ERROR);
                }
                // Réinitialiser la table après suppression
                initialize(null, null);
            }
        } catch (Exception e) {
            // Afficher un message d'erreur si une exception est levée
            Directories.alert("An error occurred while deleting the price: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    public void onShowAddPage(ActionEvent actionEvent) {
        try {
            // Show the add parking prices container
            toggleContainers(true,false);
            // Initialize the choice boxes with data
            initializeComboBox(categoryChoixBox, new VehCategoryDb().getListOfCategory(),
                    e -> ((VehicleCategory) e).getCategory_name());
            initializeComboBox(durationChoixBox, new PrgDurationDb().getListOfDuration(),
                    obj -> ((ParkingDuration) obj).getDuration_value() + " Hour");
        } catch (Exception e) {
            // Display an error message if initialization fails
            Directories.alert("An error occurred while opening the add page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    public void onShowUpdatePage(ParkingPrice price) {
        priceFocus = price; // Assigner l'élément sélectionné
        if (priceFocus == null) {
            Directories.alert("Please select a price to update.", Alert.AlertType.WARNING);
            return;
        }
        // Show the update container
        toggleContainers(false,true);
        try {
            // Initialize the choice boxes
            initializeComboBox(categoryChoixBoxForUpdate, new VehCategoryDb().getListOfCategory(),
                    e -> ((VehicleCategory) e).getCategory_name());
            initializeComboBox(durationChoixBoxForUpdate, new PrgDurationDb().getListOfDuration(),
                    obj -> ((ParkingDuration) obj).getDuration_value() + " Hour");
            // Get the category name and duration value for the selected price
            String categoryName = new VehCategoryDb().getCategoryNameById(priceFocus.getCategory_id());
            String durationValue = priceFocus.getDuration_value() + " Hour";

            // Populate the fields with the existing data
            categoryChoixBoxForUpdate.getSelectionModel().select(categoryName);
            durationChoixBoxForUpdate.getSelectionModel().select(durationValue);
            priceValueFieldForUpdate.setText(String.valueOf(priceFocus.getPrice_value()));

        } catch (Exception e) {
            Directories.alert("An error occurred while initializing the page: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private boolean isNotValidateFields(ComboBox<String> categoryBox, ComboBox<String> durationBox, TextField priceField) {
        if (categoryBox.getSelectionModel().isEmpty() || durationBox.getSelectionModel().isEmpty() || priceField.getText().isEmpty()) {
            Directories.alert("Please fill in all fields.", Alert.AlertType.ERROR);
            return true;
        }
        try {
            Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            Directories.alert("Please enter a valid price value.", Alert.AlertType.ERROR);
            return true;
        }
        return false;
    }
    private void toggleContainers(boolean showAdd, boolean showUpdate) {
        addParkingPricesContainer.setVisible(showAdd);
        updateParkingPricesContainer.setVisible(showUpdate);
    }


}
