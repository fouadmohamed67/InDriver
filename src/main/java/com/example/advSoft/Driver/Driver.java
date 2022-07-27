package com.example.advSoft.Driver;

import com.example.advSoft.User.User;

public class Driver extends User {
    public  String drive_license;
    public  String nationalID;

    public String getNationalID() {
        return nationalID;
    }
    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }
    public String getDrive_license() {
        return drive_license;
    }
    public void setDrive_license(String drive_license) {
        this.drive_license = drive_license;
    }
}
