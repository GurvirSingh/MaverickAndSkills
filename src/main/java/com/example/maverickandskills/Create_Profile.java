package com.example.maverickandskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Create_Profile extends AppCompatActivity {
    EditText createProName,createProMajor,createProDOB,createProPhoneno,createProEmail,createProStags,createProBio;
    Button createProSubmit;
    Profiles prof;
    DatabaseReference reff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);

        createProName = (EditText)findViewById(R.id.createProName);
        createProMajor = (EditText)findViewById(R.id.createProMajor);
        createProDOB = (EditText)findViewById(R.id.createProDOB);
        createProPhoneno = (EditText)findViewById(R.id.createProPhoneno);
        createProEmail = (EditText)findViewById(R.id.createProEmail);
        createProStags = (EditText)findViewById(R.id.createProStags);
        createProBio = (EditText)findViewById(R.id.createProBio);


        createProSubmit = (Button)findViewById(R.id.createProSubmit);
        prof = new Profiles();
        reff = FirebaseDatabase.getInstance().getReference().child("Profiles");

        createProSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prof.setCreateProBio(createProName.getText().toString().trim());
                prof.setCreateProMajor(createProMajor.getText().toString().trim());
                prof.setCreateProDOB(createProDOB.getText().toString().trim());
                prof.setCreateProPhoneno(createProPhoneno.getText().toString().trim());
                prof.setCreateProEmail(createProEmail.getText().toString().trim());
                prof.setCreateProStags(createProStags.getText().toString().trim());
                prof.setCreateProBio(createProBio.getText().toString().trim());

                reff.push().setValue(prof);
                Toast.makeText(Create_Profile.this ,"Profile Created Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Create_Profile.this, MainActivity.class));


            }
        });







//        configureBackButton();
    }







//    private void configureBackButton()
//    {
//        Button backButton = (Button) findViewById(R.id.backButton);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                finish();
//            }
//        });
//
//    }
}
