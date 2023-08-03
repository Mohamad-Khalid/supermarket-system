package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.Bill;
import com.gomarket.supermarket.models.ProductRepository;
import com.gomarket.supermarket.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {
    @FXML
    Button btnBack , btnBuy , btnPrint ;

    @FXML
    TableView prodTable;
    @FXML
    TableColumn colProd;

    @FXML
    TextField txtSearchedName, txtPrice , txtDiscount, txtNumber, txtTotal;

    @FXML
    TextArea billArea;
    @FXML
    Label txtWarning;

    ProductRepository productRepository = new ProductRepository();
    BadInputBlocker badInputBlocker = new BadInputBlocker();
    Product selectedProduct = new Product();
    DataSetter dataSetter = new DataSetter();
    Bill bill = new Bill();

    int toPrintQuantity;
    double toPrintTotal;


    public void preventCharInput(){
        badInputBlocker.preventCharInput(txtNumber);
    }

    public void search(){
        prodTable.getItems().clear();
        prodTable.getItems().addAll(productRepository.getSearchedProducts(txtSearchedName.getText()));
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }


    public void selectProduct(){
        txtWarning.setText("");
        Product product = (Product) prodTable.getSelectionModel().getSelectedItem();
        dataSetter.setProductForm(product,txtPrice,txtDiscount);
        selectedProduct = product;
    }


    public void printTotal(){
        if(txtNumber.getText() != null && !txtNumber.getText().isEmpty()){

            int wantedQuantity = Integer.parseInt(txtNumber.getText());

            if(wantedQuantity == 0) {
               Utility.clearForm(txtNumber,txtTotal);
            }

            double total = bill.calculateTotal(selectedProduct,wantedQuantity);
            toPrintTotal = total;
            toPrintQuantity = wantedQuantity;
            txtTotal.setText(total+"");
            if(total == 0 ){
                dataSetter.setWarning(selectedProduct,txtWarning);
                Utility.clearForm(txtTotal,txtNumber);
            }
        }
        else {
            Utility.clearForm(txtTotal);
        }
    }

    public void buy(){
        if(txtNumber.getText()!=null && txtPrice.getText().length()>0 && txtNumber.getText().length()>0){
            selectedProduct.setNumber(selectedProduct.getNumber() - toPrintQuantity); // Updated the quantity of this product
            productRepository.update(selectedProduct);
            String billAreaContent = billArea.getText().length() > 0 ? billArea.getText()+"\n" : "";
            billArea.setText( billAreaContent + bill.generateBill(selectedProduct,toPrintQuantity,toPrintTotal));
            Utility.clearForm(txtPrice,txtTotal,txtDiscount,txtNumber);
        }
    }

    public void printBillContent() {
       DataPrinter.printBillContent(billArea);
    }

    public void refreshServicePage(ActionEvent event) throws IOException {
       Utility.moveTo(event,"Services");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProd.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTable.setItems(productRepository.getAllProducts());
    }
}
