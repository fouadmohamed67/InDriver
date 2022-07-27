package com.example.advSoft.Discount.NumOfClientsDiscount;

import com.example.advSoft.Discount.Discount;
import com.example.advSoft.Discount.IDiscountDatabaseConnect;
import com.example.advSoft.Discount.NumOfClientsDiscount.NClientDatabaseConnect;

import java.sql.SQLException;


public class NclientsDiscount implements Discount {
    @Override
    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {
        IDiscountDatabaseConnect DBDiscount=new NClientDatabaseConnect();
        return DBDiscount.getDiscount(clientName);
    }
}
