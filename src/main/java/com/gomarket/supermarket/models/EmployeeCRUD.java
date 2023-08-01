package com.gomarket.supermarket.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EmployeeCRUD {

    public static Employee fillEmployeeInfo(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        if(resultSet.next()){
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setCity(resultSet.getString("city"));
            employee.setSalary(resultSet.getDouble("salary"));
            employee.setPhoneNumber(resultSet.getString("phoneNumber"));
            employee.setJoinDate(resultSet.getDate("joinDate").toLocalDate());
        }
        return employee;
    }
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
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee delete failed!");
            }

            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public ObservableList getAllEmployees(){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Employee");
            while (results.next()){
                Employee tempObj = fillEmployeeInfo(results);
                employees.add(tempObj);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }

        return  employees;
    }

    public static ObservableList getSearchedEmployees(String name){
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Employee WHERE name like '%"+name +"%'");
            while (results.next()){
                Employee tempObj = fillEmployeeInfo(results);
                employees.add(tempObj);
            }
            DBConnection.closeDBConnection();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  employees;
    }
}
