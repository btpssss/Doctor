package com.example.doctorside;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DoctorHomePage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationdoctor);

        replaceFragment(new DoctorHomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_patient_history:
                        replaceFragment(new PatientHistoryFragment());
                        return true;
                    case R.id.navigation_manage_slot:
                        replaceFragment(new ManageSlotFragment());
                        return true;
                    default:
                        replaceFragment(new DoctorHomeFragment());
                        return true;
                }


            }
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.doctorhomeframelayout,fragment);
        fragmentTransaction.commit();

    }


}