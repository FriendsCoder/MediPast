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
import com.friendscoder.icare.adapters.MedicalHistoryAdapter;
import com.friendscoder.icare.dbhelpers.DoctorDbHelper;
import com.friendscoder.icare.models.MedicalHistory;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryView extends Fragment {
    private ListView medicalHistoryListView;
    private MedicalHistoryAdapter medicalHistoryAdapter;
    private DoctorDbHelper medicalHistoryDb;
    private List<MedicalHistory> medicalHistoryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctors_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medicalHistoryListView = view.findViewById(R.id.doctors_list);
        medicalHistoryList = loadData();
        medicalHistoryAdapter = new MedicalHistoryAdapter(getActivity(), medicalHistoryList);
        medicalHistoryListView.setAdapter(medicalHistoryAdapter);
    }

    private List<MedicalHistory> loadData() {
        medicalHistoryDb = new DoctorDbHelper(getActivity());
        Cursor cursor = medicalHistoryDb.getMedicalHistory();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(medicalHistoryDb.KEY_HISTORY_ID));
                String name = cursor.getString(cursor.getColumnIndex(medicalHistoryDb.KEY_HISTORY_NAME));
                String details = cursor.getString(cursor.getColumnIndex(medicalHistoryDb.KEY_HISTORY_DETAILS));
                String prescriptionPhoto = cursor.getString(cursor.getColumnIndex(medicalHistoryDb.KEY_HISTORY_PRESCRIPTION_PHOTO));
                String appointmentDate = cursor.getString(cursor.getColumnIndex(medicalHistoryDb.KEY_HISTORY_DATE));
                MedicalHistory medicalHistory = new MedicalHistory(name, details, prescriptionPhoto, appointmentDate);
                medicalHistoryList.add(medicalHistory);
                medicalHistoryDb.close();
            } while (cursor.moveToNext());
        }
        return medicalHistoryList;
    }
}
