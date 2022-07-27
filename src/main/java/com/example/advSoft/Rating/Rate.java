package com.example.advSoft.Rating;

public class Rate {
    int rideId;
    float rate;
    String clientName;
    String driverName;

    public Rate(int rideId, float rate, String clientName, String driverName) {
        this.rideId = rideId;
        this.rate = rate;
        this.clientName = clientName;
        this.driverName = driverName;
    }

    public Rate() {

    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
