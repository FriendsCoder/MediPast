package com.friendscoder.icare.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.friendscoder.icare.R;

import com.friendscoder.icare.dbhelpers.DoctorDbHelper;


public class AddDoctors extends Fragment {
    private Button btnSave, btnCancel;
    private EditText etName, etDetails, etAppointmentDate, etPhoneNumber, etEmail;
    DoctorDbHelper doctorDb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String details = etDetails.getText().toString().trim();
                String appointmentDate = etAppointmentDate.getText().toString().trim();
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                doctorDb = new DoctorDbHelper(getActivity());
                boolean isInserted = doctorDb.insertDoctor(name, details, appointmentDate, phoneNumber, email);
                if (isInserted == true) {
                    Toast.makeText(getActivity(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "cancel Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialization(View view) {
        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);
        etName = view.findViewById(R.id.et_doctors_name);
        etDetails = view.findViewById(R.id.et_doctors_details);
        etAppointmentDate = view.findViewById(R.id.et_appointment_date);
        etPhoneNumber = view.findViewById(R.id.et_phone_number);
        etEmail = view.findViewById(R.id.et_email);
    }
}
