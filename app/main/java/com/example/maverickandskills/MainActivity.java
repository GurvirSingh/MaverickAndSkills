package com.example.maverickandskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast.makeText(MainActivity.this, "Firebase Connection Success", Toast.LENGTH_LONG).show();

        configureNext();
    }

    private void configureNext() {

        Button settingsPage = (Button) findViewById(R.id.settingsPage);
        settingsPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, profileActivity.class));
            }
        });

        Button settingsPagex= (Button) findViewById(R.id.settingsPage3);
        settingsPagex.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, aboutUs.class));
            }
        });

//        Button registerButton = (Button) findViewById(R.id.registerButton);
//        registerButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//            }
//        });

//
//        Button loginButton = (Button) findViewById(R.id.loginButton);
//        loginButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                startActivity(new Intent(MainActivity.this, Create_Profile.class));
//            }
//        });
        Button eventsTabButton = (Button) findViewById(R.id.eventsTabButton);
        eventsTabButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, mainEvent.class));
            }
        });

        Button settingsPage1 = (Button) findViewById(R.id.settingsPage2);
        settingsPage1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, createNewProfile.class));
            }
        });
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
