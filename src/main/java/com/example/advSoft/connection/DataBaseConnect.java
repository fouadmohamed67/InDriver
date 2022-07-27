package com.example.advSoft.connection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface DataBaseConnect {
    public Connection establish_connection() throws SQLException, ClassNotFoundException;
    public void set(JSONObject object) throws SQLException, ClassNotFoundException;
    public void delete(int id) throws SQLException, ClassNotFoundException;
    public JSONObject get(int id) throws SQLException, ClassNotFoundException;
    public JSONArray listAll() throws SQLException, ClassNotFoundException;
    public void update(JSONObject temp , int id) throws SQLException, ClassNotFoundException;
}
