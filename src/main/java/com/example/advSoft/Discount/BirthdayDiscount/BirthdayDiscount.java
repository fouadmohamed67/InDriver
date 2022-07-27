package com.example.advSoft.Discount.BirthdayDiscount;

import com.example.advSoft.Discount.Discount;
import com.example.advSoft.Discount.IDiscountDatabaseConnect;

import java.sql.*;


public class BirthdayDiscount implements Discount {
    @Override
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {
        IDiscountDatabaseConnect DBDiscount=new BirthdayDatabaseConnect();
         return DBDiscount.getDiscount(clientName);
    }
}
