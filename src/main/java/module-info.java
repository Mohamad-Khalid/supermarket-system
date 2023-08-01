module com.gomarket.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.gomarket.supermarket to javafx.fxml;
    exports com.gomarket.supermarket;
    exports com.gomarket.supermarket.controllers;

    opens com.gomarket.supermarket.controllers to javafx.fxml;
    exports com.gomarket.supermarket.models;

    opens com.gomarket.supermarket.models to javafx.fxml;
}