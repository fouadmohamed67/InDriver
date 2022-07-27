package com.example.advSoft.Offer;

import com.example.advSoft.Client.ClientController;
import com.example.advSoft.Client.ClientService;
import com.example.advSoft.Ride.IReqRideDatabaseConnect;
import com.example.advSoft.Ride.IRideDatabaseConnect;
import com.example.advSoft.Ride.ReqRideDatabaseConnect;
import com.example.advSoft.Ride.RideDatabaseConnect;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OfferController implements OfferService{
    IOfferDatabaseConnect dbOffer = new OfferDatabaseConnect();
    IReqRideDatabaseConnect dbReqRide = new ReqRideDatabaseConnect();
    IRideDatabaseConnect dbRide = new RideDatabaseConnect();

    @RequestMapping("viewOffers/{clientName}")
    @Override
    @GetMapping
    public String viewOffers(@PathVariable("clientName") String clientName) throws SQLException, ClassNotFoundException {
        return dbOffer.viewOffersOfClient(clientName);
    }

    @RequestMapping("acceptOffer/{id}")
    @Override
    @PostMapping
    public void acceptOffer(@PathVariable("id") int id) throws SQLException, ClassNotFoundException
    {
        ClientService client = new ClientController();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        JSONObject offer = new JSONObject();
        JSONObject reqride = new JSONObject();
        JSONObject ride = new JSONObject();

        offer = dbOffer.get(id);
        System.out.println(offer.get("requestedrides_id").toString());
        String temp = offer.get("requestedrides_id").toString();
        int reqId = Integer.parseInt(temp);
        offer.put("accepted", 1);
        offer.put("accepted_time", dtf.format(now));
        dbOffer.update(offer, id);

        reqride = dbReqRide.get(reqId);

        reqride.put("accepted", 1);
        Double discount_value=client.checkDiscount((String) reqride.get("clientName"));
        dbReqRide.update(reqride, reqId);
        ride.put("id", id);
        float price = BigDecimal.valueOf(offer.getDouble("price")).floatValue();



        ride.put("discount_value",discount_value*100);
        ride.put("discount_percent", discount_value);
        Double price_after_discount=price-price*discount_value;
        ride.put("price_after_discount",price_after_discount);
        dbRide.set(ride);
    }
    @RequestMapping("sendOffer/{id}")
    @Override
    @PostMapping
    public void sendOffer(@PathVariable("id") int id,@RequestBody  String req) throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(req);
        jsonObject.put("requestedrides_id", id);
        if(dbRide.checkOpenedRide(jsonObject.get("driverName").toString()))
            System.out.println("You can't send offer while you are in a Trip");
        else
            dbOffer.set(jsonObject);
    }

}
