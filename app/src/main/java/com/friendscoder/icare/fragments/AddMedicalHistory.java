package com.friendscoder.icare.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.friendscoder.icare.R;
import com.friendscoder.icare.dbhelpers.DoctorDbHelper;
import com.friendscoder.icare.models.MedicalHistory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class AddMedicalHistory extends Fragment {
    ImageView ivPrescription;
    Button btnTakePrescription, btnDate, btnSave, btnCancel;
    EditText  etDetails;
    AutoCompleteTextView etDoctorName;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String prescriptionPhoto, date;
    private DatePickerDialog datePickerDialog;
    DoctorDbHelper medicalHistoryDb;
    private ArrayList<String> doctorName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_medical_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

//        List<String> doctorName=loadAllDoctorName();
        String[] arr={""};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.select_dialog_item, arr);

        etDoctorName.setThreshold(2);
        etDoctorName.setAdapter(adapter);

        btnTakePrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = etDoctorName.getText().toString().trim();
                String doctorDetails = etDetails.getText().toString().trim();
                medicalHistoryDb = new DoctorDbHelper(getActivity());
                boolean isInserted = medicalHistoryDb.insertMedicalHistory(doctorName, doctorDetails, prescriptionPhoto, date);
                if (isInserted == true) {
                    Toast.makeText(getActivity(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to insert data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "-" + month + "-" + year;
                btnDate.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Context context = getActivity();
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPrescription.setImageBitmap(imageBitmap);
            ivPrescription.setVisibility(View.VISIBLE);
            prescriptionPhoto = encodeToBase64(imageBitmap, Bitmap.CompressFormat.JPEG, 100);

        }
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    private void initialization(View view) {
        ivPrescription = view.findViewById(R.id.iv_prescription);
        btnTakePrescription = view.findViewById(R.id.btn_take_prescription);
        etDoctorName = view.findViewById(R.id.et_h_doctor_name);
        etDetails = view.findViewById(R.id.et_h_doctor_details);
        btnDate = view.findViewById(R.id.btn_h_date);
        btnCancel = view.findViewById(R.id.btn_h_cancel);
        btnSave = view.findViewById(R.id.btn_h_save);
    }
//    private List<String> loadAllDoctorName() {
//        ArrayList<String> nameList=new ArrayList<>();
//        medicalHistoryDb = new DoctorDbHelper(getActivity());
//        Cursor cursor = medicalHistoryDb.getAllDoctorName();
//        if (cursor.moveToFirst()) {
//            do {
//               String name = cursor.getString(cursor.getColumnIndex(medicalHistoryDb.KEY_NAME));
//               nameList.add(name);
//               Toast.makeText(getContext(),name,Toast.LENGTH_SHORT).show();
//                medicalHistoryDb.close();
//            } while (cursor.moveToNext());
//        }
//        return nameList;
//    }

}
