package com.example.maverickandskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText email_id, password;
    Button registerButton;
    FirebaseAuth fAuth;
    ProgressBar progerssBar;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        define variables which get values from the UI
        email_id = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        registerButton = findViewById(R.id.loginButton);
        textView3 = findViewById(R.id.textView4);

        fAuth = FirebaseAuth.getInstance();
        progerssBar = findViewById(R.id.progressBar);


        progerssBar.setVisibility(View.GONE);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

//        set on click listener when user clicks register button
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String email = email_id.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String emailPattern = "([a-zA-Z0-9]+)([\\.{1}])?([a-zA-Z0-9]+)\\@mavs.uta([\\.])edu";

                if(TextUtils.isEmpty(email)) {
                    email_id.setError("Email ID is required!");
                    return;
                }
                if(TextUtils.isEmpty(pass)) {
                    password.setError("Password is required!");
                    return;
                }
                if(!(email_id.getText().toString().trim().matches(emailPattern))) {
                    email_id.setError("Email Should be @mavs.uta.edu");
                    return;
                }

                if(pass.length() < 8){
                    password.setError("Password must be greater than 8 characters");
                    return;

                }

                progerssBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        AlertDialog.Builder b = new AlertDialog.Builder(Register.this);
                        b.setMessage("Are you sure you want to register with " + email).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(task.isSuccessful()){
                                    fAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                Toast.makeText( Register.this, "Successfully Registered! Please verify your email", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), Login.class));
                                            }
                                            else {
                                                Toast.makeText( Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                else {
                                    Toast.makeText(Register.this, "Some Error Occured! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    progerssBar.setVisibility(View.GONE);
                                }
                            }
                        }).setNegativeButton("No",null);

                        AlertDialog al = b.create();
                        al.show();

                    }
                });
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}
