package com.badie.pms.controller;

import com.badie.pms.model.VehicleCategory;
import com.badie.pms.util.GlobalFunction;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ParkingDurationManagement extends GlobalFunction implements Initializable {
    public AnchorPane mainContainer;
    public TableColumn<VehicleCategory,String> durationId,durationValue;
    public TableColumn<VehicleCategory, String> durationCreatedAt,durationUpdateAt;
    public TableView<VehicleCategory> tableDuration;
    public TextField searchField;
    public List<VehicleCategory> listOfDuration;
    public Label toDashboardPageBtn1, toDurationPageBtn, toDashboardPageBtn2;
    public AnchorPane addParkingDurationContainer;
    public TextField addDurationField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onClickMenuNav(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == toDashboardPageBtn1 || mouseEvent.getSource() == toDashboardPageBtn2){
            ((BorderPane)(mainContainer.getParent())).setCenter(AdminDashboard.dashboardViewStatic);
        }else if(mouseEvent.getSource() == toDurationPageBtn){
            addParkingDurationContainer.setVisible(false);
        }
    }
    public void onSearchCategory(KeyEvent keyEvent) {
    }

    public void addParkingDuration(ActionEvent actionEvent) {
    }
    public void onShowAddDurationPage(ActionEvent actionEvent) {
        addParkingDurationContainer.setVisible(true);
    }

}
