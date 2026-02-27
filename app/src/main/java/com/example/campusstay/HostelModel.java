package com.example.campusstay;

public class HostelModel {

    public String hostelName, location, rent, rooms;

    public HostelModel() {
        // required empty constructor
    }

    public HostelModel(String hostelName, String location, String rent, String rooms) {
        this.hostelName = hostelName;
        this.location = location;
        this.rent = rent;
        this.rooms = rooms;
    }
}


