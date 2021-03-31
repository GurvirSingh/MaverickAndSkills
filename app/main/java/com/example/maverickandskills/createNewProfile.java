package com.example.maverickandskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class createNewProfile extends AppCompatActivity {

    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_profile);

        fAuth = FirebaseAuth.getInstance();


        configureNext();
    }



    private void configureNext() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Button settingsPage = (Button) findViewById(R.id.aboutUs3);
        settingsPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(createNewProfile.this, aboutUs.class));
            }
        });

        Button settingsPage5 = (Button) findViewById(R.id.aboutUs);
        settingsPage5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(createNewProfile.this, Create_Profile.class));
            }
        });

        Button settingsPage6 = (Button) findViewById(R.id.aboutUs2);
        settingsPage6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(createNewProfile.this, updateProfileNew.class));
            }
        });

        Button deleteAccount = (Button) findViewById(R.id.deleteAccount);
        deleteAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder b = new AlertDialog.Builder(createNewProfile.this);
                b.setMessage("Are you sure you want to delete your account? ").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText( createNewProfile.this, "Account Deleted Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Register.class));
                                }
                                else {
                                    Toast.makeText( createNewProfile.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).setNegativeButton("No",null);

                AlertDialog al = b.create();
                al.show();
            }
        });
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText( createNewProfile.this, "Successfuly Logged out!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}
