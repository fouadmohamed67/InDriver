package com.example.advSoft.Offer;

import com.example.advSoft.connection.DataBaseConnect;

import java.sql.SQLException;

public interface IOfferDatabaseConnect extends DataBaseConnect {
    public String viewOffersOfClient(String clientName) throws SQLException, ClassNotFoundException;
}
