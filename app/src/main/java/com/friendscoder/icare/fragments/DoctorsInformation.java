package com.friendscoder.icare.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.friendscoder.icare.R;
import com.friendscoder.icare.UpdateDoctorActivity;
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

        doctorsInformationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialogEditDelete=new Dialog(getActivity());
                dialogEditDelete.setContentView(R.layout.dialoag_update_delete);
                WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialogEditDelete.getWindow().getAttributes());
                layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height=WindowManager.LayoutParams.WRAP_CONTENT;
                dialogEditDelete.getWindow().setAttributes(layoutParams);
                dialogEditDelete.show();

                TextView tvEdit = (TextView) dialogEditDelete.findViewById(R.id.tv_dialog_edit);
                TextView tvDelete = (TextView) dialogEditDelete.findViewById(R.id.tv_dialog_delete);
                tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Doctor doctor=doctorsList.get(position);
                        Intent intent=new Intent(getActivity(), UpdateDoctorActivity.class);
                        intent.putExtra("doctor",doctor);
                        getActivity().startActivity(intent);
                        dialogEditDelete.dismiss();
                    }
                });
                return true;
            }
        });
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

                Doctor doctor = new Doctor(id,name, details, appointmentDate, phoneNumber, email);
                doctorsList.add(doctor);
                doctorsDb.close();
            } while (cursor.moveToNext());
        }
        return doctorsList;
    }

}
