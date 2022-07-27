package com.example.advSoft.Discount;

import com.example.advSoft.connection.DataBaseConnect;

import java.sql.SQLException;

public interface IDiscountDatabaseConnect  extends DataBaseConnect {
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException;
}
