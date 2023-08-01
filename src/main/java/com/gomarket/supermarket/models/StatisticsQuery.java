package com.gomarket.supermarket.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsQuery {

    public Employee fillEmployee(ResultSet resultSet) throws SQLException {
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

    public ResultSet getQeryResults(String query){
        ResultSet resultSet = null;
        try {
            Statement statement = DBConnection.openDBConnection().createStatement();
            resultSet = statement.executeQuery(query);
            DBConnection.closeDBConnection();
        }catch (Exception e){
            System.out.println(e);
        }
        return resultSet;
    }

    public int getCountOf(String entity) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) AS count FROM "+ entity;
        ResultSet resultSet = getQeryResults(query);
        if(resultSet.next())
            count = resultSet.getInt("count");
        return count;
    }

    public int maxMin(String compare , String property  , String entity) throws SQLException {
        String query = "SELECT "+ compare +"("+property+") As count FROM "+entity;
        ResultSet resultSet = getQeryResults(query);
        int result = 0;
        if(resultSet.next())
            result = resultSet.getInt("count");
        return result;
    }

    public Employee getOldestEmployee() throws SQLException {
        String query = "SELECT * FROM employee WHERE joindate = (SELECT MIN(joindate) FROM employee)";
        ResultSet resultSet = getQeryResults(query);
        Employee employee = fillEmployee(resultSet);
        return employee;
    }


}
