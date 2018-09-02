package com.friendscoder.icare.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.friendscoder.icare.R;
import com.friendscoder.icare.models.MedicalHistory;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryAdapter extends ArrayAdapter<MedicalHistory> {

    private Context context;
    private List<MedicalHistory> histories;

    public MedicalHistoryAdapter(@NonNull Context context, List<MedicalHistory> histories) {
        super(context, R.layout.item_medical_history, histories);
        this.context=context;
        this.histories=histories;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.item_medical_history,parent,false);

        TextView tvDoctorName=convertView.findViewById(R.id.tv_history_name);
        TextView tvDetails=convertView.findViewById(R.id.tv_history_details);
        TextView tvDate=convertView.findViewById(R.id.tv_history_date);
        ImageView ivPres=convertView.findViewById(R.id.iv_history_prescription);

        tvDoctorName.setText(histories.get(position).getDoctorName());
        tvDetails.setText(histories.get(position).getDoctorDetails()+" specialist");
        tvDate.setText("Date "+histories.get(position).getDate());
        ivPres.setImageBitmap(base64ToBitmap(histories.get(position).getPrescriptionPhoto()));

        return convertView;
    }

    private Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}
