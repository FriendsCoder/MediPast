package com.friendscoder.icare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.friendscoder.icare.dbhelpers.DoctorDbHelper;
import com.friendscoder.icare.models.Doctor;

public class UpdateDoctorActivity extends AppCompatActivity {

    private Doctor doctor;


    TextView etDVName, etDetail, etDate, etPhoneNumber, etEmail;

    Button btnUpdate, btnEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);

        final DoctorDbHelper  dbHelper=new DoctorDbHelper(this);


        btnUpdate=findViewById(R.id.btnUpdate);
        etDVName=findViewById(R.id.etDVName);
        etDetail=findViewById(R.id.etDetails);
        etDate=findViewById(R.id.etApoinmentDate);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);

        etEmail=findViewById(R.id.etEmail);
        final Doctor doctor= (Doctor) getIntent().getSerializableExtra("doctor");



        etDVName.setText(doctor.getName());
        etDetail.setText(doctor.getDetails());
        etDate.setText(doctor.getAppointmentDate());
        etPhoneNumber.setText(doctor.getPhoneNumber());
        etEmail.setText(doctor.getEmail());







        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etDVName.getText().toString().trim();
                String details=etDetail.getText().toString().trim();
                String date=etDate.getText().toString().trim();
                String phone=etPhoneNumber.getText().toString().trim();
                String email=etEmail.getText().toString().trim();
//Toast.makeText(getApplicationContext(),"ID is: "+doctor.getId(),Toast.LENGTH_SHORT).show();
                Doctor doctor1=new Doctor(doctor.getId(),name,details,date,phone,email);

                boolean status=dbHelper.updateDoctor(doctor1);



                if (status){
                    Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(UpdateDoctorActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
