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

public class createE extends AppCompatActivity {
    EditText eventName, addUser, eventDate, eventTime, eventDesc;
    Button submitevent;
    EventsList eList;
    DatabaseReference reff ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_e);
        eventName = (EditText)findViewById(R.id.eventName);
        addUser = (EditText)findViewById(R.id.addUser);
        eventDate = (EditText)findViewById(R.id.eventDate);
        eventTime = (EditText)findViewById(R.id.eventTime);
        eventDesc = (EditText)findViewById(R.id.eventDesc);
        submitevent = (Button)findViewById(R.id.submitevent);
        eList = new EventsList();
        reff = FirebaseDatabase.getInstance().getReference().child("EventsList");

        submitevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eList.setEventName(eventName.getText().toString().trim());
                eList.setAddUser(addUser.getText().toString().trim());
                eList.setEventDate(eventDate.getText().toString().trim());
                eList.setEventTime(eventTime.getText().toString().trim());
                eList.setEventDesc(eventDesc.getText().toString().trim());

                reff.push().setValue(eList);
                Toast.makeText(createE.this ,"Event Added Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(createE.this, Event_Page.class));


            }
        });

        configureBackButton();
    }
//    private void configureNext() {
//        Button submitevent = (Button) findViewById(R.id.submitevent);
//        submitevent.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                startActivity(new Intent(createE.this, Event_Page.class));
//            }
//        });
//
//    }

    private void configureBackButton()
    {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                finish();
            }
        });

    }
}
