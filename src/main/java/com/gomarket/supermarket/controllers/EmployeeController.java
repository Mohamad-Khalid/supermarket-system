package com.gomarket.supermarket.controllers;
import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.EmployeeCRUD;
import com.gomarket.supermarket.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;


public class EmployeeController implements Initializable {
    @FXML
    TextField txtEName , txtENumber ,txtESalary;

    @FXML
    ComboBox txtECity;

    @FXML
    DatePicker txtEDate;

    @FXML
    Button btnAdd , btnUpdate , btnDelete,btnBack;

    @FXML
    TableColumn colID ,colName ,colNumber ,colSalary,colCity,colDate ;

    @FXML
    TableView eTable;
    @FXML
    TextField btnEnName;



    BadInputBlocker badInputBlocker = new BadInputBlocker();

    EmployeeCRUD employeeCRUD = new EmployeeCRUD();
    int employeeID;
    String [] cities = {"Cairo","Alex","Asuit"};
    ObservableList olist = FXCollections.observableArrayList(cities);

    public void preventCharInput(){
        badInputBlocker.preventCharInput(txtENumber);
    }
    public void preventNonDoubleInput(){
        badInputBlocker.preventNonDoubleInput(txtESalary);
    }

    public void search(){
        eTable.setItems(EmployeeCRUD.getSearchedEmployees(btnEnName.getText()));
    }

    public void addEmployee() throws ParseException {
        Employee employee = new Employee();
        if(badInputBlocker.notEmptyData(txtEDate,txtECity,txtEName,txtENumber,txtESalary)){
            employee.setName(txtEName.getText());
            employee.setPhoneNumber(txtENumber.getText());
            employee.setSalary(Double.parseDouble(txtESalary.getText()));
            employee.setCity(txtECity.getValue()+"");
            employee.setJoinDate( txtEDate.getValue());
            employeeCRUD.insert(employee);
            eTable.setItems(employeeCRUD.getAllEmployees());
            Utility.clearForm(txtEName,txtENumber,txtESalary);
        }
    }

    public void updateEmployee(){
        if(badInputBlocker.notEmptyData(txtEDate,txtECity,txtEName,txtENumber,txtESalary)){
            Employee employee = new Employee();
            employee.setName(txtEName.getText());
            employee.setPhoneNumber(txtENumber.getText());
            employee.setSalary(Double.parseDouble(txtESalary.getText()));
            employee.setCity(txtECity.getValue()+"");
            employee.setJoinDate(txtEDate.getValue());
            employee.setId(employeeID);
            employeeCRUD.update(employee);
            eTable.setItems(employeeCRUD.getAllEmployees());
            Utility.clearForm(txtEName,txtENumber,txtESalary);
        }
    }
    public void deleteEmployee(){
        employeeCRUD.delete(employeeID);
        eTable.setItems(employeeCRUD.getAllEmployees());
        Utility.clearForm(txtEName,txtENumber,txtESalary);
    }

    public void selectEmployeeFromTable(){
        Employee employee = (Employee) eTable.getSelectionModel().getSelectedItem();
        txtEName.setText(employee.getName());
        txtENumber.setText( employee.getPhoneNumber()+"");
        txtESalary.setText(employee.getSalary()+"");
        txtECity.setValue(employee.getCity()+"");
        txtEDate.setValue(  employee.getJoinDate());
        employeeID = employee.getId();
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtECity.getItems().addAll(olist);
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("joinDate"));
        eTable.setItems(employeeCRUD.getAllEmployees());
    }


}
