package com.example.advSoft.connection;

import org.json.JSONObject;

import java.sql.SQLException;

public interface ILoginDataBaseConnect {
    public JSONObject login(String person) throws SQLException, ClassNotFoundException;
}
