package com.example.advSoft.Driver;

import com.example.advSoft.User.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.SQLException;

public interface DriverService {
    User driver = new Driver();

    public String listAllRequestedRides(@PathVariable("DriverName") String DriverName) throws SQLException, ClassNotFoundException;
    public String listAllDriverRides(@PathVariable("username") String drivername) throws SQLException, ClassNotFoundException;
    public void setFavAreas( @RequestBody String area ,@PathVariable("driverName") String driverName) throws SQLException, ClassNotFoundException;
    public void sendOffer(@PathVariable("id") int id, @RequestBody  String req) throws SQLException, ClassNotFoundException;
    public void startTrip(@PathVariable("id") int id) throws SQLException, ClassNotFoundException;
    public void endTrip(@PathVariable("id") int id) throws SQLException, ClassNotFoundException;
}
