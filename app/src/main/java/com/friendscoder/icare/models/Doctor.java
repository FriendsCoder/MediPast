package com.friendscoder.icare.models;

import java.io.Serializable;

public class Doctor implements Serializable{
    private int id;
    private String name;
    private String details;
    private String appointmentDate;
    private String phoneNumber;
    private String email;

    public Doctor() {
    }

    public Doctor(String name, String details, String appointmentDate, String phoneNumber, String email) {
        this.name = name;
        this.details = details;
        this.appointmentDate = appointmentDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Doctor(int id, String name, String details, String appointmentDate, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.appointmentDate = appointmentDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
