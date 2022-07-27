package com.example.advSoft.Discount;

import java.sql.SQLException;

public interface Discount {

    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException;
}
