package com.gomarket.supermarket.controllers;
import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.ProductRepository;
import com.gomarket.supermarket.models.Product;
import com.gomarket.supermarket.models.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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




    ProductRepository productRepository = new ProductRepository();
    BadInputBlocker badInputBlocker = new BadInputBlocker();
    DataSetter dataSetter = new DataSetter();
    Validator validator = new Validator();
    int prodID;

    public void search(){
        pTable.setItems(productRepository.getSearchedProducts(btnEnName.getText()));
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
        if(!validator.isEmptyData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount)){
            Product product = dataSetter.setProductData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount);
            productRepository.insert(product);
            pTable.setItems(productRepository.getAllProducts());
            Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
        }
    }

    public void update(){
        if(!validator.isEmptyData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount)){
            Product product = dataSetter.setProductData(txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount);
            product.setId(prodID);
            productRepository.update(product);
            pTable.setItems(productRepository.getAllProducts());
            Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
        }
    }
    public void delete(){
        productRepository.delete(prodID);
        pTable.setItems(productRepository.getAllProducts());
        Utility.clearForm(txtPName,txtPNumber,txtPPrice,txtPDiscount);
    }

    public void selectProductFromTable(){
        Product product = (Product) pTable.getSelectionModel().getSelectedItem();
        dataSetter.setProductForm(product,txtPType,txtPName,txtPNumber,txtPPrice,txtPDiscount);
        prodID = product.getId();
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtPType.getItems().addAll(dataSetter.setProductTypes());
        dataSetter.setProductTableColumns(colID,colName,colNumber,colPrice,colType,colDiscount);
        pTable.setItems(productRepository.getAllProducts());
    }


}
