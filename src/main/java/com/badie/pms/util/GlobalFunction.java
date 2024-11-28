package com.badie.pms.util;

import com.badie.pms.model.ParkingPrice;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;

public abstract class GlobalFunction {
    public TableColumn<?, Void> getAcitionTableColumn() {
        TableColumn<Object,Void> actionColumn = new TableColumn<>("Actions");
        actionColumn.setMinWidth(120);
        actionColumn.setPrefWidth(130);
        actionColumn.setMaxWidth(140);
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Edit");
            private final HBox buttons = new HBox(5, updateButton, deleteButton);
            {
                // Style des boutons
                updateButton.getStyleClass().add("update-btn");
                deleteButton.getStyleClass().add("delete-btn");
                // Action pour supprimer
                deleteButton.setOnAction(e -> {
                    onClickActionDelete(getTableView().getItems().get(getIndex())); // Méthode pour supprimer
                });
                // Action pour mettre à jour
                updateButton.setOnAction(e -> {
                    onClickActionUpdate(getTableView().getItems().get(getIndex())); // Méthode pour mettre à jour
                });
                buttons.setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttons);
                }
            }
        });
        return actionColumn;
    }
    public abstract void onClickActionDelete(Object item);
    public abstract void onClickActionUpdate(Object item);
}
