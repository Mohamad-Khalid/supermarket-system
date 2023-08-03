package com.gomarket.supermarket.controllers;
import com.gomarket.supermarket.models.BadInputBlocker;
import com.gomarket.supermarket.models.EmployeeRepository;
import com.gomarket.supermarket.models.Employee;
import com.gomarket.supermarket.models.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    Validator validator = new Validator();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    DataSetter dataSetter = new DataSetter();
    int employeeID;


    public void preventCharInput(){
        badInputBlocker.preventCharInput(txtENumber);
    }
    public void preventNonDoubleInput(){
        badInputBlocker.preventNonDoubleInput(txtESalary);
    }

    public void search(){
        eTable.setItems(EmployeeRepository.getSearchedEmployees(btnEnName.getText()));
    }

    public void addEmployee() throws ParseException {
        if(!validator.isEmptyData(txtEDate,txtECity,txtEName,txtENumber,txtESalary)){
            Employee employee = dataSetter.setEmployeeData(txtECity,txtEDate,txtEName,txtENumber,txtESalary);
            employeeRepository.insert(employee);
            eTable.setItems(employeeRepository.getAllEmployees());
            Utility.clearForm(txtEName,txtENumber,txtESalary);
        }
    }

    public void updateEmployee(){
        if(!validator.isEmptyData(txtEDate,txtECity,txtEName,txtENumber,txtESalary)){
            Employee employee = dataSetter.setEmployeeData(txtECity,txtEDate,txtEName,txtENumber,txtESalary);
            employee.setId(employeeID);
            employeeRepository.update(employee);
            eTable.setItems(employeeRepository.getAllEmployees());
            Utility.clearForm(txtEName,txtENumber,txtESalary);
        }
    }
    public void deleteEmployee(){
        employeeRepository.delete(employeeID);
        eTable.setItems(employeeRepository.getAllEmployees());
        Utility.clearForm(txtEName,txtENumber,txtESalary);
    }

    public void selectEmployeeFromTable(){
        Employee employee = (Employee) eTable.getSelectionModel().getSelectedItem();
        dataSetter.setEmployeeForm(employee,txtECity,txtEDate,txtEName,txtENumber,txtESalary);
        employeeID = employee.getId();
    }

    public void goBack(ActionEvent event) throws IOException {
        Utility.moveTo(event,"Home");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtECity.getItems().addAll(dataSetter.setEmplooyeeCities());
        dataSetter.setEmployeeTableColumns(colID,colName,colNumber,colSalary,colCity,colDate);
        eTable.setItems(employeeRepository.getAllEmployees());
    }


}
