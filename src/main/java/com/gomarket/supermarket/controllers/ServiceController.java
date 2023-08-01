package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.ProductCRUD;
import com.gomarket.supermarket.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
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
    TextArea bill;
    @FXML
    Label txtWarning;

    ProductCRUD productCRUD = new ProductCRUD();
    BadInputBlocker badInputBlocker = new BadInputBlocker();

    Product selectedProduct = new Product();



    int toPrintQuantity;
    double toPrintTotal;


    public void preventCharInput(){
        badInputBlocker.preventCharInput(txtNumber);
    }

    public void search(){
        prodTable.getItems().clear();
        prodTable.getItems().addAll(productCRUD.getSerchedProducts(txtSearchedName.getText()));
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }


    public void clickTable(){
        txtWarning.setText("");
        Product product = (Product) prodTable.getSelectionModel().getSelectedItem();
        txtPrice.setText(product.getPrice()+"");
        txtDiscount.setText(product.getDiscount()+"");
        selectedProduct = product;
    }

    public void calculateTotal(){
        if(txtNumber.getText() != null && !txtNumber.getText().isEmpty()){
            int wantedQuantity = Integer.parseInt(txtNumber.getText());
            int availableQuantity = selectedProduct.getNumber();
            if(availableQuantity >= wantedQuantity){
                double total = (selectedProduct.getPrice() * wantedQuantity) - (selectedProduct.getDiscount()/100.0 * selectedProduct.getPrice());
                toPrintTotal = total;
                toPrintQuantity = wantedQuantity;
                txtTotal.setText(total+"");
            }
            else{
                txtWarning.setText("Available is "+selectedProduct.getNumber());
                Utility.clearForm(txtTotal,txtNumber);
            }
        }
        else {
            Utility.clearForm(txtTotal);
        }




    }

    public void buy(){
        if(txtNumber.getText()!=null && txtPrice.getText().length()>0 && txtNumber.getText().length()>0){
            selectedProduct.setNumber(selectedProduct.getNumber() - toPrintQuantity);
            productCRUD.update(selectedProduct);
            String billContent = bill.getText().length()>0? bill.getText()+"\n" : "";
            bill.setText( billContent +
                    "---------------Bill ---------------"+
                    "\nProduct Name : " + selectedProduct.getName() +
                    "\nProduct Price : "+ selectedProduct.getPrice()+
                    "\n" + "Quantity : "+ toPrintQuantity+
                    "\nTotal : "        + toPrintTotal
            );

            Utility.clearForm(txtPrice,txtTotal,txtDiscount,txtNumber);
        }
    }


    public void printBillContent() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean success = job.printPage(bill);
            if (success) {
                job.endJob();
            }
        }
    }

    public void refreshServicePage(ActionEvent event) throws IOException {
       Utility.moveTo(event,"Services");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProd.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodTable.setItems(productCRUD.getAllProducts());
    }
}
