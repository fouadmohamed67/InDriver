package com.example.advSoft.Driver;

import com.example.advSoft.Offer.IOfferDatabaseConnect;
import com.example.advSoft.Offer.OfferController;
import com.example.advSoft.Offer.OfferDatabaseConnect;
import com.example.advSoft.Offer.OfferService;
import com.example.advSoft.Ride.IReqRideDatabaseConnect;
import com.example.advSoft.Ride.IRideDatabaseConnect;
import com.example.advSoft.Ride.ReqRideDatabaseConnect;
import com.example.advSoft.Ride.RideDatabaseConnect;
import com.example.advSoft.User.UserServices;
import com.example.advSoft.connection.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.sql.SQLException;


@RequestMapping("/api/driver")
@RestController
public class DriverController implements DriverService , UserServices {
    DataBaseConnect dbDriver = new DriverDatabaseConnect();
    ILoginDataBaseConnect dbLogin = new DriverDatabaseConnect();
    IRideDatabaseConnect dbRide = new RideDatabaseConnect();
    IReqRideDatabaseConnect dbReqRide = new ReqRideDatabaseConnect();
    DataBaseConnect dbFavAreas = new FavAreasDatabaseConnect();
    OfferService offer = new OfferController();
    @Autowired
    DriverController(){

    }

    //TEST REQUIRED
    @Override
    @GetMapping
    public String getAll() throws ClassNotFoundException, SQLException {
        JSONArray allDrivers = new JSONArray();
        allDrivers = dbDriver.listAll();
        return allDrivers.toString();
    }

    @RequestMapping("register")
    @PostMapping
    @Override
    public void register( @RequestBody String person) throws SQLException, ClassNotFoundException, SQLException {
        JSONObject jsonObject = new JSONObject(person);
        dbDriver.set(jsonObject);
    }

    @RequestMapping("login")
    @Override
    @PostMapping
    public String login(String person) throws SQLException, ClassNotFoundException, SQLException {
        JSONObject jsonObject = dbLogin.login(person);
        return jsonObject.toString();
    }

    @RequestMapping("FavAreas/{driverName}")
    @Override
    @PostMapping
    public void setFavAreas( @RequestBody String area ,@PathVariable("driverName") String driverName) throws SQLException, ClassNotFoundException {
        JSONArray jsonArray = new JSONArray(area);
        for (int i = 0; i < jsonArray.length(); i++)
        {
            String temp = jsonArray.getString(i);
            JSONObject obj = new JSONObject();
            obj.put("area", temp);
            obj.put("driverName", driverName);
            dbFavAreas.set(obj);
        }
        System.out.println("The Favorite Area/s added successfully");
    }

    //TEST REQUIRED
    @RequestMapping("listAllRides/{drivername}")
    @Override
    @GetMapping
    public String listAllDriverRides(@PathVariable("drivername") String drivername) throws SQLException, ClassNotFoundException {
        String rides = dbRide.listAllRides(drivername);
        return rides;
    }

    @RequestMapping("listAllRequestedRides/{DriverName}")
    @Override
    @GetMapping
    public String listAllRequestedRides(@PathVariable("DriverName") String DriverName) throws SQLException, ClassNotFoundException {
        return dbReqRide.listrequestedToDriver(DriverName);
    }

    @RequestMapping("sendOffer/{id}")
    @Override
    @PostMapping
    public void sendOffer(@PathVariable("id") int id,@RequestBody  String req) throws SQLException, ClassNotFoundException {
        offer.sendOffer(id,req);
    }

    //TEST REQUIRED
    @RequestMapping("startTrip/{id}")
    @Override
    @PostMapping
    public void startTrip(@PathVariable("id") int id) throws SQLException, ClassNotFoundException
    {
        JSONObject jsonObject = dbRide.get(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        String currentTime=now.toString();
        jsonObject.remove("arrive_source_time");
        jsonObject.put("arrive_source_time", currentTime);
         dbRide.update(jsonObject, id);
    }

    //TEST REQUIRED
    @RequestMapping("endTrip/{id}")
    @Override
    @PostMapping
    public void endTrip(@PathVariable("id") int id) throws SQLException, ClassNotFoundException
    {
        JSONObject jsonObject = dbRide.get(id);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime=now.toString();
        jsonObject.remove("arrive_destination_time");
        jsonObject.put("arrive_destination_time", currentTime);
         dbRide.update(jsonObject, id);
    }
}
