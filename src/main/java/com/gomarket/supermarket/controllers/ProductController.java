package com.gomarket.supermarket.controllers;
import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.ProductCRUD;
import com.gomarket.supermarket.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ProductController implements Initializable {
    @FXML
    TextField txtPName , txtPNumber,txtPPrice,txtPDiscount,btnEnName;

    @FXML
    ComboBox txtPType;

    @FXML
    Button btnPAdd , btnPUpdate , btnPDelete , btnBack;


   @FXML
    TableColumn colID ,colName,colNumber,colPrice,colType,colDiscount;

    @FXML
    TableView pTable;


    String [] productTypes = {"Food","Fruits","Drinks"};
    ObservableList olist = FXCollections.observableArrayList(productTypes);

    ProductCRUD productCRUD = new ProductCRUD();
    BadInputBlocker badInputBlocker = new BadInputBlocker();
    int prodID;

    public void search(){
        pTable.setItems(productCRUD.getSerchedProducts(btnEnName.getText()));
    }

    public void preventCharInput(){
        badInputBlocker.preventCharInput(txtPNumber);
        badInputBlocker.preventCharInput(txtPDiscount);
    }
    public void preventNonDoubleInput(){
        badInputBlocker.preventNonDoubleInput(txtPPrice);
    }
    public void preventInvalidDiscountValue(){
        badInputBlocker.preventInvalidDiscountValue(txtPDiscount);
    }


    public void add(){
        if(badInputBlocker.notEmptyData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount)){
            System.out.println("not empty data");
            Product product = new Product();
            product.setName(txtPName.getText());
            product.setNumber(Integer.parseInt(txtPNumber.getText()));
            product.setPrice(Double.parseDouble(txtPPrice.getText()));
            product.setType(txtPType.getValue()+"");
            product.setDiscount(Integer.parseInt(txtPDiscount.getText()));
            productCRUD.insert(product);
            pTable.setItems(productCRUD.getAllProducts());
            Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
        }
    }

    public void update(){
        if(badInputBlocker.notEmptyData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount)){
            Product product = new Product();
            product.setName(txtPName.getText());
            product.setNumber(Integer.parseInt(txtPNumber.getText()));
            product.setPrice(Double.parseDouble(txtPPrice.getText()));
            product.setType(txtPType.getValue()+"");
            product.setDiscount(Integer.parseInt(txtPDiscount.getText()));
            product.setId(prodID);
            productCRUD.update(product);
            pTable.setItems(productCRUD.getAllProducts());
            Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
        }
    }
    public void delete(){
        productCRUD.delete(prodID);
        pTable.setItems(productCRUD.getAllProducts());
        Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
    }

    public void selectProductFromTable(){
        Product product = (Product) pTable.getSelectionModel().getSelectedItem();
        txtPName.setText(product.getName());
        txtPNumber.setText( product.getNumber()+"");
        txtPPrice.setText(product.getPrice()+"");
        txtPDiscount.setText(product.getDiscount()+"");
        txtPType.setValue(product.getType());
        prodID = product.getId();
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPType.getItems().addAll(olist);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        pTable.setItems(productCRUD.getAllProducts());
    }


}
