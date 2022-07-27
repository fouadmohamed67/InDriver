package com.example.advSoft.Discount.AreaDiscount;

import com.example.advSoft.Discount.Discount;
import com.example.advSoft.Discount.IDiscountDatabaseConnect;

import java.sql.*;


public class AreaDiscount implements Discount {

    @Override
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {
        IDiscountDatabaseConnect DBDiscount=new AreaDiscountDatabseConnect();
       return DBDiscount.getDiscount(clientName);
    }
}