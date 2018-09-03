package com.friendscoder.icare.models;

import java.io.Serializable;

public class MedicalHistory implements Serializable{
    private int id;
    private String doctorName;
    private String doctorDetails;
    private String prescriptionPhoto;
    private String date;

    public MedicalHistory() {
    }

    public MedicalHistory(String doctorName, String doctorDetails, String prescriptionPhoto, String date) {
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.prescriptionPhoto = prescriptionPhoto;
        this.date = date;
    }

    public MedicalHistory(int id, String doctorName, String doctorDetails, String prescriptionPhoto, String date) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorDetails = doctorDetails;
        this.prescriptionPhoto = prescriptionPhoto;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorDetails() {
        return doctorDetails;
    }

    public void setDoctorDetails(String doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    public String getPrescriptionPhoto() {
        return prescriptionPhoto;
    }

    public void setPrescriptionPhoto(String prescriptionPhoto) {
        this.prescriptionPhoto = prescriptionPhoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
