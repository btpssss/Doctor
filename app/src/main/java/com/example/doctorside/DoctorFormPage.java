package com.example.doctorside;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorFormPage extends AppCompatActivity {

    Button doctorContinue;
    TextView hellodoctor;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    EditText doctorSpecialization;
    EditText doctorClinicAddress;
    EditText doctorEmail;
    EditText doctorExprience;
    EditText doctorEducation;
    RadioGroup radioGroupDoctor;
    DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_form_page);
        hellodoctor = (TextView) findViewById(R.id.hello_doctor);
        doctorContinue = (Button) findViewById(R.id.doctor_form_button_continue);
        String doctorname  = getIntent().getStringExtra("name");
        hellodoctor.setText("Hello Dr. " + doctorname);
        doctorSpecialization = (EditText) findViewById(R.id.doctor_specialization);
        doctorClinicAddress = (EditText) findViewById(R.id.doctor_clinic_address);
        doctorEmail = (EditText)findViewById(R.id.doctor_email_address);
        doctorEducation = (EditText) findViewById(R.id.doctor_education);
        doctorExprience = (EditText) findViewById(R.id.doctor_exprience);
        radioGroupDoctor = (RadioGroup) findViewById(R.id.radiogroup_doctor);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        doctorContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doctorSpecialization.getText().toString().isEmpty() || doctorClinicAddress.getText().toString().isEmpty() || doctorEmail.getText().toString().isEmpty() || doctorEducation.getText().toString().isEmpty() || doctorExprience.getText().toString().isEmpty() || radioGroupDoctor.getCheckedRadioButtonId() == -1){
                    Toast.makeText(DoctorFormPage.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    senddatatofirestore();
                    Intent i = new Intent(DoctorFormPage.this,DoctorHomePage.class);
                    startActivity(i);
                }


            }
        });
    }

    public void senddatatofirestore(){
        documentReference = firebaseFirestore.collection("Doctors").document(firebaseAuth.getCurrentUser().getPhoneNumber());
        if(!doctorSpecialization.getText().toString().isEmpty()){
            documentReference.update("Specialization", doctorSpecialization.getText().toString());
        }
        if(!doctorClinicAddress.getText().toString().isEmpty()){
            documentReference.update("Clinic Address", doctorClinicAddress.getText().toString());
        }
        if(!doctorEmail.getText().toString().isEmpty()){
            documentReference.update("E-mail address", doctorEmail.getText().toString());
        }
        if(!doctorEducation.getText().toString().isEmpty()){
            documentReference.update("Education" , doctorEducation.getText().toString());
        }
        if(!doctorExprience.getText().toString().isEmpty()){
            documentReference.update("Exprience" , doctorExprience.getText().toString());
        }
        documentReference.update("Gender" , ((RadioButton)findViewById(radioGroupDoctor.getCheckedRadioButtonId())).getText().toString());


    }
}