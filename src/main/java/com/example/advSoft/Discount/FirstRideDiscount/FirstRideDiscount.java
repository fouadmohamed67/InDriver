package com.example.advSoft.Discount.FirstRideDiscount;

import com.example.advSoft.Discount.Discount;
import com.example.advSoft.Discount.IDiscountDatabaseConnect;

import java.sql.SQLException;


public class FirstRideDiscount implements Discount {

    @Override
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {
        IDiscountDatabaseConnect DBDiscount=new FirstRideDiscountDatabseConnect();
        return DBDiscount.getDiscount(clientName);
    }

}
