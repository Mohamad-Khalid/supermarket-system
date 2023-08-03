package com.gomarket.supermarket.models;

import com.gomarket.supermarket.controllers.DataSetter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeRepository {
    static DataSetter dataSetter = new DataSetter();

    public void insert(Employee employee){
        try {
            String sqlInsert = "insert into Employee values(?,?,?,?,?,?)";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlInsert);
            statement.setInt(1,employee.getId());
            statement.setString(2,employee.getName());
            statement.setString(3,employee.getPhoneNumber());
            statement.setDouble(4,employee.getSalary());
            statement.setString(5,employee.getCity());
            statement.setDate(6, Date.valueOf(employee.getJoinDate()));
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee inserted successfully!");
            } else {
                System.out.println("Employee insertion failed!");
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void update(Employee employee){
        try {
            String sqlUpdate = "update Employee set name=? , phoneNumber =? , salary=? , city=? , joinDate=? where id="+employee.getId();
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlUpdate);
            statement.setString(1,employee.getName());
            statement.setString(2,employee.getPhoneNumber());
            statement.setDouble(3,employee.getSalary());
            statement.setString(4,employee.getCity());
            statement.setDate(5, Date.valueOf(employee.getJoinDate()));
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully!");
            } else {
                System.out.println("Employee update failed!");
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void delete(int id){
        try {
            String sqlDelete = "delete from Employee where id=?";
            PreparedStatement statement = DBConnection.openDBConnection().prepareStatement(sqlDelete);
            statement.setInt(1,id);
            statement.executeUpdate();
            DBConnection.closeDBConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public ObservableList getAllEmployees(){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");
            while (resultSet.next()){
                Employee employee = dataSetter.setEmployeeData(resultSet);
                employees.add(employee);
            }
            DBConnection.closeDBConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return  employees;
    }

    public static ObservableList getSearchedEmployees(String name){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee WHERE name like '%"+name +"%'");
            while (resultSet.next()){
                Employee employee = dataSetter.setEmployeeData(resultSet);
                employees.add(employee);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  employees;
    }
}
