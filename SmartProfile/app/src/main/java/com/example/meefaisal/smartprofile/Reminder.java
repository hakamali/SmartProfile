package com.example.meefaisal.smartprofile;

/**
 * Created by MeeFaisal on 3/18/2018.
 */

class Reminder {
    private String name;
    private String date;
    private String time;
    private String address;
    private String message;

    public Reminder(String name, String date, String time, String address, String message) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.address = address;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
