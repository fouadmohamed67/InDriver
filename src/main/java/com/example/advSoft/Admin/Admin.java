package com.example.advSoft.Admin;

import java.sql.SQLException;
import java.util.List;
import com.example.advSoft.Client.Client;
import com.example.advSoft.Driver.Driver;
import org.springframework.web.bind.annotation.RequestBody;

public class Admin {

    List<Client> clients;
    List<Driver> drivers ;

    public List<Client> getClients() {
        return clients;
    }
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    public List<Driver> getDrivers() {
        return drivers;
    }
    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

}
