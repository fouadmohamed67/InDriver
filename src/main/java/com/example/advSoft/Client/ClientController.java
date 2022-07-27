package com.example.advSoft.Client;
 import com.example.advSoft.Discount.*;
 import com.example.advSoft.Discount.AreaDiscount.AreaDiscount;
 import com.example.advSoft.Discount.BirthdayDiscount.BirthdayDiscount;
 import com.example.advSoft.Discount.FirstRideDiscount.FirstRideDiscount;
 import com.example.advSoft.Discount.NumOfClientsDiscount.NclientsDiscount;
 import com.example.advSoft.Discount.OfficialHolidaysDiscount.OfficialHolidays;
 import com.example.advSoft.Offer.OfferController;
 import com.example.advSoft.Offer.OfferService;
 import com.example.advSoft.Ride.RideController;
 import com.example.advSoft.Ride.RideService;
 import com.example.advSoft.User.UserServices;
 import com.example.advSoft.connection.DataBaseConnect;
 import com.example.advSoft.connection.ILoginDataBaseConnect;
 import org.json.JSONArray;
 import org.json.JSONObject;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import java.sql.*;
 import java.util.ArrayList;
 import java.util.List;

@RequestMapping("/api/client")
@RestController
public class ClientController  implements  ClientService , UserServices {
    DataBaseConnect dbClient = new ClientDatabaseConnect();
    ILoginDataBaseConnect dbLogin = new ClientDatabaseConnect();
    OfferService offer = new OfferController();
    RideService ride = new RideController();
    
    @Autowired
    public ClientController(){

    }

    //TEST REQUIRED
    @Override
    @GetMapping
    public String getAll() throws SQLException, ClassNotFoundException {
        JSONArray allClients = new JSONArray();
        allClients = dbClient.listAll();
        return allClients.toString();
    }

    @RequestMapping("register")
    @Override
    @PostMapping
    public void register( @RequestBody String person) throws  ClassNotFoundException, SQLException {
        JSONObject jsonObject = new JSONObject(person);
        dbClient.set(jsonObject);
    }

    @RequestMapping("login")
    @Override
    @PostMapping
    public String login(String person) throws  ClassNotFoundException, SQLException {
        JSONObject jsonObject =   dbLogin.login(person);
        return jsonObject.toString();
    }

    @RequestMapping("RequestRide")
    @Override
    @PostMapping
    public void RequestRide(@RequestBody String req) throws SQLException, ClassNotFoundException {
        ride.RequestRide(req);
    }

    @RequestMapping("viewOffers/{clientName}")
    @Override
    @GetMapping
    public String viewOffers(@PathVariable("clientName") String clientName) throws SQLException, ClassNotFoundException {
       return offer.viewOffers(clientName);
    }

    @RequestMapping("acceptOffer/{id}")
    @Override
    @PostMapping
    public void acceptOffer(@PathVariable("id") int id) throws SQLException, ClassNotFoundException
    {
        offer.acceptOffer(id);
    }

    @RequestMapping("checkDiscount/{ClientName}")
    @Override
    @GetMapping
    public Double checkDiscount(@PathVariable("ClientName") String ClientName) throws SQLException, ClassNotFoundException
    {
         List<Double> discounts = new ArrayList<Double>();
        JSONArray arr = new JSONArray();
        Discount discount1 = new BirthdayDiscount();
        discounts.add(discount1.getDiscount(ClientName));
        Discount discount2 = new NclientsDiscount();
        discounts.add(discount2.getDiscount(ClientName));
        Discount discount3 = new AreaDiscount();
        discounts.add(discount3.getDiscount(ClientName));
        Discount discount4 = new FirstRideDiscount();
        discounts.add(discount4.getDiscount(ClientName));
        Discount discount5 = new OfficialHolidays();
        discounts.add(discount5.getDiscount(ClientName));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Birthday Discount", discounts.get(0));
        jsonObject.put("Number of clients Discount", discounts.get(1));
        jsonObject.put("Area Discount", discounts.get(2));
        jsonObject.put("First time Discount", discounts.get(3));
        jsonObject.put("Holiday Discount", discounts.get(4));
        arr.put(jsonObject);
        System.out.println(arr.toString());
        Double total=(Double) discounts.get(0)+ (Double)discounts.get(1)+(Double)discounts.get(2)+(Double)discounts.get(3)+(Double)discounts.get(4);
        System.out.println(total);
         return  total;
    }

}
