package com.example.advSoft.Ride;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public interface RideService {
    Ride ride = new Ride();
    public String showDriverPrice(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String showAcceptedPriceOffer(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String driverArriveToSource(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public String driverArriveToDestination(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException;
    public void RequestRide(@RequestBody String req) throws SQLException, ClassNotFoundException;
    }
