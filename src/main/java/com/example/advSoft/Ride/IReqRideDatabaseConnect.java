package com.example.advSoft.Ride;

import com.example.advSoft.connection.DataBaseConnect;

import java.sql.SQLException;

public interface IReqRideDatabaseConnect extends DataBaseConnect {
    public String listrequestedToDriver(String driverName) throws SQLException, ClassNotFoundException;
}
