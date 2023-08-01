package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.models.StatisticsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {
    @FXML
    ComboBox queryMenu;
    @FXML
    TextArea textArea;

    StatisticsQuery statisticsQury = new StatisticsQuery();
    String [] queries = {"Number of Products",
                         "Number of Employees",
                         "Max Employee Salary",
                         "Max Product Price",
                         "Min Product Price",
                         "The Oldest Employee"};
    ObservableList olist = FXCollections.observableArrayList(queries);


    public void executeQuery() throws SQLException {

        if(queryMenu.getValue() == queries[0]){
            int productCount = statisticsQury.getCountOf("Product");
            textArea.setText("Number of Products : " + productCount);
        }
        if(queryMenu.getValue() == queries[1]){
            int productCount = statisticsQury.getCountOf("Employee");
            textArea.setText("Number of Employees : " + productCount);
        }
        if(queryMenu.getValue() == queries[2]){
            int maxEmpSal = statisticsQury.maxMin("max","salary","employee");
            textArea.setText("Max Employee Salary : " + maxEmpSal );
        }
        if(queryMenu.getValue() == queries[3]){
            int maxProdPrice = statisticsQury.maxMin("max","price","product");
            textArea.setText("Max Product Price : " + maxProdPrice );
        }
        if(queryMenu.getValue() == queries[4]){
            int minProdPrice = statisticsQury.maxMin("min","price","product");
            textArea.setText("Max Product Price : " + minProdPrice );
        }

        if(queryMenu.getValue() == queries[5]){
            String result = statisticsQury.getOldestEmployee().toString();
            textArea.setText("Oldest Employee is : " + result);
        }





    }




    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        queryMenu.setItems(olist);
    }
}
