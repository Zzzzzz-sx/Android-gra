package com.example.myapplication;

import android.content.Context;
import android.view.View;

public class Patient extends View {

    private String patient_name;
    private String patient_id;
    private String patient_phonenum;
    public Patient(Context context) {
        super(context);
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_phonenum() {
        return patient_phonenum;
    }

    public void setPatient_phonenum(String patient_phonenum) {
        this.patient_phonenum = patient_phonenum;
    }
}
