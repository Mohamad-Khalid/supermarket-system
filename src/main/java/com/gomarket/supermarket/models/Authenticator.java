package com.gomarket.supermarket.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authenticator {
    public boolean Authenticated(Admin admin) throws SQLException {
        String query = "select * from admin where username = ? and password = ?";
        PreparedStatement st = DBConnection.openDBConnection().prepareStatement(query);
        st.setString(1,admin.getUsername());
        st.setString(2,admin.getPassword());
        ResultSet results = st.executeQuery();
        if(results.next())
            return true;
        return false;
    }
}
