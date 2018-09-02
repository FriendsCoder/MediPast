package com.friendscoder.icare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.friendscoder.icare.models.Doctor;

public class UpdateDoctorActivity extends AppCompatActivity {

    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);

        doctor= (Doctor) getIntent().getSerializableExtra("doctor");
        initialization();
    }

    private void initialization() {

    }
}
