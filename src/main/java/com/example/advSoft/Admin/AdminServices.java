package com.example.advSoft.Admin;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.SQLException;
import java.util.List;
@Service
public interface AdminServices {
    Admin admin = new Admin();
    public void verifyDriverRegistration(@RequestBody String req) throws SQLException, ClassNotFoundException;
    public String listPendingDrivers() throws SQLException, ClassNotFoundException;
    public void suspendAccountClient(@RequestBody String req) throws SQLException, ClassNotFoundException;
    public void suspendAccountDriver(@RequestBody String req) throws SQLException, ClassNotFoundException;
    public void setDiscountArea(@RequestBody String areas) throws SQLException, ClassNotFoundException;
    public String showDriverPrice(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String showAcceptedPriceOffer(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String driverArriveToSource(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String driverArriveToDestination(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;

    }
