package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.models.Authenticator;
import com.gomarket.supermarket.models.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    Authenticator authenticator = new Authenticator();
    public void login(ActionEvent event) throws SQLException, IOException {
        admin.setUsername(txtUSER.getText());
        admin.setPassword(txtPASS.getText());
        if(authenticator.Authenticated(admin)){
            Utility.moveTo(event,"Home");
        }
        else {
            lblMSG.setText("Wrong Username or Password");
        }
    }


}
