package com.example.advSoft.Client;

import com.example.advSoft.User.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.SQLException;

public interface ClientService {

    User client = new Client();
    public void RequestRide(@RequestBody String req) throws SQLException, ClassNotFoundException;
    public Double checkDiscount(@RequestBody String req) throws SQLException, ClassNotFoundException;
    public String viewOffers(@PathVariable("clientName") String clientName) throws SQLException, ClassNotFoundException;
    public void acceptOffer(@PathVariable("id") int id) throws SQLException, ClassNotFoundException;
}
