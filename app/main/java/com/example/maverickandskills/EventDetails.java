package com.example.maverickandskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Intent intent = getIntent();

        final String name = intent.getStringExtra("Name");
        String date = intent.getStringExtra("Date");
        String time = intent.getStringExtra("Time");
        String desc = intent.getStringExtra("Desc");

        TextView ename = (TextView) findViewById(R.id.name);
        TextView edate = (TextView) findViewById(R.id.date);
        TextView etime = (TextView) findViewById(R.id.time);
        TextView edesc = (TextView) findViewById(R.id.desc);

        ename.setText(name);
        edate.setText(date);
        etime.setText(time);
        edesc.setText(desc);


        Button settingsPage = (Button) findViewById(R.id.joinChat);
        settingsPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.chatroom");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
//                startActivity(new Intent(EventDetails.this, profileActivity.class));
            }
        });

        final Button joinEvent = (Button) findViewById(R.id.joinEvent);
        joinEvent.setTag(1);
        joinEvent.setText("Join Event");
        joinEvent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final int status =(Integer) v.getTag();
                if(status == 1) {
                    joinEvent.setText("Joined");
                    Toast.makeText( EventDetails.this, "Successfully Joined " + name, Toast.LENGTH_SHORT).show();

                    v.setTag(0); //pause
                } else {
                    joinEvent.setText("Join");
                    Toast.makeText( EventDetails.this, "Removed Event " + name, Toast.LENGTH_SHORT).show();
                    v.setTag(1); //pause
                }
            }
        });
    }
}
