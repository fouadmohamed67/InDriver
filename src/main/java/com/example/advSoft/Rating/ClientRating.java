package com.example.advSoft.Rating;

import com.example.advSoft.connection.DataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api/rate")
@RestController
public class ClientRating implements IClientRating, RateService{
    List<Float> driverRatings = new ArrayList<Float>();
    DataBaseConnect dbRating = new RatingDatabaseConnect();
    // didn't get the driver name yet


    @RequestMapping("/{id}")
    @PostMapping
    @Override
    public void addRate(@PathVariable int id, @RequestBody String req  )throws SQLException, ClassNotFoundException {
        JSONObject jsonObject = new JSONObject(req);
         jsonObject.put("id", id);
        dbRating.set(jsonObject);
    }
    @RequestMapping("viewDriverAvgRating/{driverName}")
    @GetMapping
    @Override
    public float viewAvg( @PathVariable String driverName) throws SQLException, ClassNotFoundException {

        String driverRating=viewRating(driverName);
        JSONArray jsonArray=new JSONArray(driverRating);
        Float avg= Float.valueOf(0);
        for (int i=0;i<jsonArray.length();i++)
        {
             float rate_float = BigDecimal.valueOf(jsonArray.getJSONObject(i).getDouble("rate")).floatValue();
             avg+=rate_float;
        }
        System.out.println(avg);
        return avg/jsonArray.length();
    }
    @RequestMapping("viewDriverRatings/{driverName}")
    @GetMapping
    @Override
    public String viewRating(@PathVariable String driverName) throws SQLException, ClassNotFoundException {
        JSONArray allRatings = new JSONArray();
        JSONArray driverRating = new JSONArray();
        allRatings = dbRating.listAll();
        for(int i=0;i<allRatings.length();i++){


            String RatedDeiver= (String) allRatings.getJSONObject(i).get("driverName");
             if(RatedDeiver.equals(driverName))
            {
                JSONObject clientRate=new JSONObject();
                clientRate.put("clientName", (String) allRatings.getJSONObject(i).get("clientName"));
                clientRate.put("rate",allRatings.getJSONObject(i).get("rate"));
                driverRating.put(clientRate);
            }

        }
        return  driverRating.toString();


    }
}
