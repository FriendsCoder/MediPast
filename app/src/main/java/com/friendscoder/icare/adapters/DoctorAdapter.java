package com.friendscoder.icare.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.friendscoder.icare.R;
import com.friendscoder.icare.models.Doctor;

import java.util.List;

public class DoctorAdapter extends ArrayAdapter<Doctor> {
    private Context context;
    private List<Doctor> doctorList;

    public DoctorAdapter(@NonNull Context context, @NonNull List<Doctor> doctorList) {
        super(context, R.layout.item_doctor, doctorList);
        this.context = context;
        this.doctorList = doctorList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        Doctor doctor = doctorList.get(position);

        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvDetails = convertView.findViewById(R.id.tv_details);
        TextView tvDate = convertView.findViewById(R.id.tv_date);
        TextView tvPhone = convertView.findViewById(R.id.tv_phone);
        TextView tvEmail = convertView.findViewById(R.id.tv_email);

        tvName.setText(doctor.getName());
        tvDetails.setText(doctor.getDetails());
        tvDate.setText(doctor.getAppointmentDate());
        tvPhone.setText(doctor.getPhoneNumber());
        tvEmail.setText(doctor.getEmail());

        return convertView;
    }
}
