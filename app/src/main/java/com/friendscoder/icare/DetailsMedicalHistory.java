package com.friendscoder.icare;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.friendscoder.icare.adapters.MedicalHistoryAdapter;
import com.friendscoder.icare.fragments.MedicalHistoryView;
import com.friendscoder.icare.models.MedicalHistory;

public class DetailsMedicalHistory extends AppCompatActivity {
    TextView tvDetailName, tvDetailDate;
    ImageView ivDetailPrescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_medical_history);

        MedicalHistory medicalHistory = (MedicalHistory) getIntent().getSerializableExtra("medicalHistory");
        initialization();
        ivDetailPrescription.setImageBitmap(MedicalHistoryAdapter.base64ToBitmap(medicalHistory.getPrescriptionPhoto()));
        tvDetailName.setText(medicalHistory.getDoctorName());
        tvDetailDate.setText(medicalHistory.getDate());
    }

    private void initialization() {
        tvDetailName = findViewById(R.id.tv_details_doctor_name);
        tvDetailDate = findViewById(R.id.tv_details_doctor_date);
        ivDetailPrescription = findViewById(R.id.iv_details_prescription);
    }
}
