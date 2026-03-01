package com.example.campusstay;

public class HostelModel {

    String hostelName;
    String location;
    String rent;
    String rooms;
    String ownerId;
    String phone;

    public HostelModel() {
    }

    public HostelModel(String hostelName, String location, String rent,
                       String rooms, String ownerId, String phone) {

        this.hostelName = hostelName;
        this.location = location;
        this.rent = rent;
        this.rooms = rooms;
        this.ownerId = ownerId;
        this.phone = phone;
    }

    public String getHostelName() { return hostelName; }
    public String getLocation() { return location; }
    public String getRent() { return rent; }
    public String getRooms() { return rooms; }
    public String getOwnerId() { return ownerId; }
    public String getPhone() { return phone; }

    public void setHostelName(String hostelName) { this.hostelName = hostelName; }
    public void setLocation(String location) { this.location = location; }
    public void setRent(String rent) { this.rent = rent; }
    public void setRooms(String rooms) { this.rooms = rooms; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public void setPhone(String phone) { this.phone = phone; }
}