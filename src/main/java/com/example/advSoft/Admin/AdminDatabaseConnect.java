package com.example.advSoft.Admin;

import com.example.advSoft.connection.DataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;


public class AdminDatabaseConnect implements DataBaseConnect {

    @Override
    public Connection establish_connection() throws SQLException, ClassNotFoundException {
        String url="jdbc:mysql://localhost:3306/sprint2";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,"root","root");
        return connection;
    }


    @Override
    public void set(JSONObject temp) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public JSONObject get(int id) {
        return null;
    }

    @Override
    public JSONArray listAll() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public void update(JSONObject temp,int id) {

    }
}
