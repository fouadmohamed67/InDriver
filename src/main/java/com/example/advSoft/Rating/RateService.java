package com.example.advSoft.Rating;

import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public interface RateService {
    Rate rate = new Rate();
    public String viewRating(String driverName) throws SQLException, ClassNotFoundException;


}
