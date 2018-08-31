package com.friendscoder.icare.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.friendscoder.icare.R;
import com.friendscoder.icare.adapters.DoctorAdapter;
import com.friendscoder.icare.dbhelpers.DoctorDbHelper;
import com.friendscoder.icare.models.Doctor;

import java.util.ArrayList;
import java.util.List;


public class DoctorsInformation extends Fragment {
    private ListView doctorsInformationListView;
    private DoctorAdapter doctorAdapter;
    private DoctorDbHelper doctorsDb;
    private List<Doctor> doctorsList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctors_information, container, false);
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doctorsInformationListView=view.findViewById(R.id.doctors_list);
        doctorsList = loadData();
        doctorAdapter = new DoctorAdapter(getActivity(), doctorsList);
        doctorsInformationListView.setAdapter(doctorAdapter);
    }

    private List<Doctor> loadData() {
        doctorsDb=new DoctorDbHelper(getActivity());
        Cursor cursor = doctorsDb.getDoctors();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(doctorsDb.KEY_ID));
                String name = cursor.getString(cursor.getColumnIndex(doctorsDb.KEY_NAME));
                String details = cursor.getString(cursor.getColumnIndex(doctorsDb.KEY_DETAILS));
                String appointmentDate = cursor.getString(cursor.getColumnIndex(doctorsDb.KEY_DATE));
                String phoneNumber = cursor.getString(cursor.getColumnIndex(doctorsDb.KEY_NUMBER));
                String email = cursor.getString(cursor.getColumnIndex(doctorsDb.KEY_EMAIL));

                Doctor doctor = new Doctor(name, details, appointmentDate, phoneNumber, email);
                doctorsList.add(doctor);
                doctorsDb.close();
            } while (cursor.moveToNext());
        }
        return doctorsList;
    }

}
