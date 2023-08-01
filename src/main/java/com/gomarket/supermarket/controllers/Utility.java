package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Utility {
    public static void moveTo(ActionEvent event , String page) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        Parent root =  FXMLLoader.load(Main.class.getResource(page+".fxml"));
        Scene scene = new Scene(root);
        stage.setTitle(page);
        stage.setScene(scene);
        stage.show();
    }

    public static void clearForm(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setText("");
        }
    }

}
