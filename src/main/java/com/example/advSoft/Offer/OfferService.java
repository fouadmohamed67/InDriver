package com.example.advSoft.Offer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public interface OfferService {
    Offer offer = new Offer();
    public String viewOffers(@PathVariable("clientName") String clientName) throws SQLException, ClassNotFoundException;
    public void acceptOffer(@PathVariable("id") int id) throws SQLException, ClassNotFoundException;
    public void sendOffer(@PathVariable("id") int id,@RequestBody  String req) throws SQLException, ClassNotFoundException;
}
