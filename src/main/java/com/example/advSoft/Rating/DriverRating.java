package com.example.advSoft.Rating;

import com.example.advSoft.connection.DataBaseConnect;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/api/rate")
@RestController
public class DriverRating implements RateService {
    List<Float> clientRatings = new ArrayList<Float>();
    DataBaseConnect dbRating = new RatingDatabaseConnect();
    @RequestMapping("viewClientRatings/{clientName}")
    @GetMapping
    @Override
    public String viewRating(@PathVariable String clientName) throws SQLException, ClassNotFoundException {

        JSONArray allRatings = new JSONArray();
        JSONArray clientRating = new JSONArray();
        allRatings = dbRating.listAll();
        for(int i=0;i<allRatings.length();i++)
        {

            String rateClient= (String) allRatings.getJSONObject(i).get("clientName");
            if(rateClient.equals(clientName))
            {
                JSONObject clientRate=new JSONObject();
                clientRate.put("ratedDriver", (String) allRatings.getJSONObject(i).get("driverName"));
                clientRate.put("rate",allRatings.getJSONObject(i).get("rate"));
                clientRating.put(clientRate);
            }

        }
        return  clientRating.toString();

    }
}
