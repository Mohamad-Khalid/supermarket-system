package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.Main;
import com.gomarket.supermarket.models.LoginAuthentication;
import com.gomarket.supermarket.models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    TextField txtUSER;
    @FXML
    PasswordField txtPASS;
    @FXML
    Label lblMSG;
    @FXML
    Button btnLOGIN , btnSIGNUP;

    Admin admin = new Admin();
    LoginAuthentication loginAuthentication = new LoginAuthentication();
    public void isSignedUP(ActionEvent event) throws SQLException, IOException {
        admin.setUsername(txtUSER.getText());
        admin.setPassword(txtPASS.getText());
        if(loginAuthentication.isLoggedIn(admin)){
            Node node = (Node) event.getSource();
            Stage stage =(Stage) node.getScene().getWindow();
            Parent root =  FXMLLoader.load(Main.class.getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Home");
            stage.setScene(scene);
            stage.show();
        }
        else {
            lblMSG.setText("Wrong Username or Password");
        }

    }


}
