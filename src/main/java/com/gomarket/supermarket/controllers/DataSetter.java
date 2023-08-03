package com.gomarket.supermarket.controllers;

import com.gomarket.supermarket.models.Employee;
import com.gomarket.supermarket.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSetter {
    public Employee setEmployeeData(ComboBox comboBox , DatePicker datePicker, TextField...textFields){
        Employee employee = new Employee();
        employee.setName(textFields[0].getText());
        employee.setPhoneNumber(textFields[1].getText());
        employee.setSalary(Double.parseDouble(textFields[2].getText()));
        employee.setCity(comboBox.getValue()+"");
        employee.setJoinDate( datePicker.getValue());
        return  employee;
    }
    public Employee setEmployeeData(ResultSet resultSet) throws SQLException {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setCity(resultSet.getString("city"));
            employee.setSalary(resultSet.getDouble("salary"));
            employee.setPhoneNumber(resultSet.getString("phoneNumber"));
            employee.setJoinDate(resultSet.getDate("joinDate").toLocalDate());

        return employee;
    }

    public void setEmployeeForm(Employee employee , ComboBox comboBox , DatePicker datePicker , TextField...textFields ){
        comboBox.setValue(employee.getCity());
        datePicker.setValue(employee.getJoinDate());
        textFields[0].setText(employee.getName());
        textFields[1].setText(employee.getPhoneNumber());
        textFields[2].setText(employee.getSalary()+"");
    }
    public void setEmployeeTableColumns(TableColumn...tableColumns){
        String [] columns = {"id","name","phoneNumber","salary","city","joinDate"};
        for (int i=0; i<tableColumns.length; i++){
            tableColumns[i].setCellValueFactory(new PropertyValueFactory<>(columns[i]));
        }
    }


    public Product setProductData(ComboBox comboBox , TextField...textFields){
        Product product = new Product();
        product.setType(comboBox.getValue()+"");
        product.setName(textFields[0].getText());
        product.setNumber(Integer.parseInt(textFields[1].getText()));
        product.setPrice(Double.parseDouble(textFields[2].getText()));
        product.setDiscount(Integer.parseInt(textFields[3].getText()));
        return  product;
    }

    public Product setProductData(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(1));
        product.setNumber(resultSet.getInt(2));
        product.setName(resultSet.getString(3));
        product.setDiscount(resultSet.getInt(4));
        product.setType(resultSet.getString(5));
        product.setPrice(resultSet.getDouble(6));
        return product;
    }


    public void setProductUpdateStatement(PreparedStatement statement , Product product) throws SQLException {
        statement.setInt(1,product.getNumber());
        statement.setString(2,product.getName());
        statement.setDouble(3,product.getDiscount());
        statement.setString(4,product.getType());
        statement.setDouble(5,product.getPrice());
    }

    public void setProductInsertStatement(PreparedStatement statement , Product product) throws SQLException {
        statement.setInt(1,product.getId());
        statement.setInt(2,product.getNumber());
        statement.setString(3,product.getName());
        statement.setDouble(4,product.getDiscount());
        statement.setString(5,product.getType());
        statement.setDouble(6,product.getPrice());
    }
    public void setProductForm(Product product , ComboBox comboBox , TextField...textFields ){
        comboBox.setValue(product.getType());
        textFields[0].setText(product.getName());
        textFields[1].setText(product.getNumber()+"");
        textFields[2].setText(product.getPrice()+"");
        textFields[3].setText(product.getDiscount()+"");
    }

    public void setProductForm(Product product , TextField...textFields ){
        textFields[0].setText(product.getPrice()+"");
        textFields[1].setText(product.getDiscount()+"");
    }

    public void setProductTableColumns(TableColumn...tableColumns){
        String [] columns = {"id","name","number","price","type","discount"};
        for (int i=0; i<tableColumns.length; i++){
            tableColumns[i].setCellValueFactory(new PropertyValueFactory<>(columns[i]));
        }
    }

    public ObservableList setEmplooyeeCities(){
        String [] cities = {"Cairo","Alex","Asuit"};
        ObservableList olist = FXCollections.observableArrayList(cities);
        return olist;
    }
    public ObservableList setProductTypes(){
        String [] productTypes = {"Food","Fruits","Drinks","Vegetables"};
        ObservableList olist = FXCollections.observableArrayList(productTypes);
        return olist;
    }

    public String[] setQueries(){
        String [] queries = {
                "Number of Products",
                "Number of Employees",
                "Max Employee Salary",
                "Max Product Price",
                "Min Product Price",
                "The Oldest Employee"};
        return queries;
    }
    public void setQueryMenu(ComboBox menu){
        ObservableList olist = FXCollections.observableArrayList(setQueries());
        menu.setItems(olist);
    }

    public void setWarning(Product product , Label warning){
        warning.setText("Available is "+product.getNumber());
    }
}
