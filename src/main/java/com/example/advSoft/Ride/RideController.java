package com.example.advSoft.Ride;

import com.example.advSoft.Offer.IOfferDatabaseConnect;
import com.example.advSoft.Offer.OfferDatabaseConnect;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RequestMapping("/api/ridemanager")
@RestController
public class RideController implements  RideService {

    IReqRideDatabaseConnect dbReqRide = new ReqRideDatabaseConnect();

    @RequestMapping("showDriverPrice/{rideID}")
    @Override
    @GetMapping
    public String showDriverPrice(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException {

        IRideDatabaseConnect RDB=new RideDatabaseConnect();
        int getRequestID=  RDB.getRequestID(rideID);
        String offers= RDB.getOffersWithReqID(getRequestID);
        return offers;

    }

    @RequestMapping("showAcceptedPriceOffer/{rideID}")
    @Override
    @GetMapping
    public String showAcceptedPriceOffer(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException {
        IOfferDatabaseConnect ODB=new OfferDatabaseConnect();
         JSONObject acceptedOffer= ODB.get(rideID);
         return acceptedOffer.toString();
    }

    @RequestMapping("driverArriveToSource/{rideID}")
    @Override
    @GetMapping
    public String driverArriveToSource(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException {
         IRideDatabaseConnect RDB=new RideDatabaseConnect();
         JSONObject ride= RDB.get(rideID);
         JSONObject RideArriveTime=new JSONObject();
         RideArriveTime.put("arrive_source_time",ride.get("arrive_source_time"));
         return RideArriveTime.toString();
    }

    @RequestMapping("driverArriveToDestination/{rideID}")
    @Override
    @GetMapping
    public String driverArriveToDestination(@PathVariable("rideID") int rideID) throws SQLException, ClassNotFoundException {
        IRideDatabaseConnect RDB=new RideDatabaseConnect();
        JSONObject ride= RDB.get(rideID);
        JSONObject RideArriveTime=new JSONObject();
        RideArriveTime.put("arrive_destination_time",ride.get("arrive_destination_time"));
        return RideArriveTime.toString();
    }
    @RequestMapping("RequestRide")
    @Override
    @PostMapping
    public void RequestRide(@RequestBody String req) throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(req);
        dbReqRide.set(jsonObject);
        System.out.println("one request created");
    }


}
