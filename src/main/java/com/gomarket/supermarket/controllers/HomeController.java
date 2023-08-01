package com.gomarket.supermarket.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeController {
    @FXML
    Button btnProducts , btnServices , btnEmployees, btnStatistics;

    public void openProducts(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Products");
    }

    public void openEmployees(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Employees");
    }

    public void openServices(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Services");
    }

    public void openStatistics(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Statistics");
    }

}
