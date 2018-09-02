package com.friendscoder.icare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.friendscoder.icare.fragments.AddDoctors;
import com.friendscoder.icare.fragments.AddMedicalHistory;
import com.friendscoder.icare.fragments.DoctorsInformation;
import com.friendscoder.icare.fragments.MedicalHistoryView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    FloatingActionButton fabAppointment, fabHistory;

    private boolean flag=true;

    private boolean fabExpanded = false;
    private FloatingActionButton fabSettings;
    private LinearLayout layoutFabSave;
    private LinearLayout layoutFabEdit;
    private LinearLayout layoutFabPhoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Appointment");


        DoctorsInformation doctorsInformation = new DoctorsInformation();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content, doctorsInformation);

        transaction.commit();

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        fabSettings = (FloatingActionButton) this.findViewById(R.id.fabSetting);
        fabAppointment=findViewById(R.id.fabAppointment);
        fabHistory=findViewById(R.id.fabHistory);

        layoutFabSave = (LinearLayout) this.findViewById(R.id.layoutFabSave);
        layoutFabEdit = (LinearLayout) this.findViewById(R.id.layoutFabEdit);

        //layoutFabSettings = (LinearLayout) this.findViewById(R.id.layoutFabSettings);

        //When main Fab (Settings) is clicked, it expands if not expanded already.
        //Collapses if main FAB was open already.
        //This gives FAB (Settings) open/close behavior
        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        closeSubMenusFab();

        fabAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addDoctor();
            }
        });

        fabHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addNewMedicalFragmemt();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setTitle("Appointment");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (!flag){
            fabSettings.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_doctor) {
           addDoctor();
        } else if (id == R.id.nav_add_medical_history) {
            addNewMedicalFragmemt();

        } else if (id == R.id.nav_doctors_info) {
            DoctorsInformation doctorsInformation = new DoctorsInformation();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_content, doctorsInformation);
            flag=false;
            transaction.addToBackStack("actvityOne");
            toolbar.setTitle("Appointment");
            closeSubMenusFab();
            fabSettings.setVisibility(View.VISIBLE);

            transaction.commit();

        } else if (id == R.id.nav_medical_history) {
            MedicalHistoryView medicalHistoryView = new MedicalHistoryView();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_content, medicalHistoryView);
            transaction.addToBackStack("actvityOne");
            closeSubMenusFab();
            fabSettings.setVisibility(View.VISIBLE);

            transaction.commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void closeSubMenusFab(){
        layoutFabSave.setVisibility(View.INVISIBLE);
        layoutFabEdit.setVisibility(View.INVISIBLE);

        fabSettings.setImageResource(R.drawable.add);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabSave.setVisibility(View.VISIBLE);
        layoutFabEdit.setVisibility(View.VISIBLE);

        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.ic_clear_white_24dp);
        fabExpanded = true;
    }

    public void addNewMedicalFragmemt(){
        AddMedicalHistory addMedicalHistory = new AddMedicalHistory();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, addMedicalHistory);
        transaction.addToBackStack("actvityOne");
        toolbar.setTitle("Add Medical History");
        closeSubMenusFab();
        flag=false;
        fabSettings.setVisibility(View.GONE);

        transaction.commit();
    }
    public void addDoctor(){
        AddDoctors addDoctors = new AddDoctors();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, addDoctors);
        transaction.addToBackStack("actvityOne");
        toolbar.setTitle("Add Doctors");
        flag=false;
        closeSubMenusFab();
        fabSettings.setVisibility(View.GONE);

        transaction.commit();
    }
}
