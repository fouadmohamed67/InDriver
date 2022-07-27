package com.example.advSoft.Discount.OfficialHolidaysDiscount;

import com.example.advSoft.Discount.Discount;
import com.example.advSoft.Discount.IDiscountDatabaseConnect;
import com.example.advSoft.Discount.OfficialHolidaysDiscount.OfficialHolidayDatabaseConnect;

import java.sql.SQLException;


public class OfficialHolidays implements Discount {

    public double getDiscount(String clientName) throws SQLException, ClassNotFoundException {
        IDiscountDatabaseConnect DBDiscount=new OfficialHolidayDatabaseConnect();
        return DBDiscount.getDiscount(clientName);
    }
}
