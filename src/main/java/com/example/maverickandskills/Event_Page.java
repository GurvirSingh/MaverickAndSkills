package com.example.maverickandskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Event_Page extends AppCompatActivity {
    DatabaseReference reff;
    private FirebaseRecyclerOptions<EventsList> options;
    private FirebaseRecyclerAdapter<EventsList,MyViewHolder> adapter;


    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__page);


//        Button Join Change
        configureNext();


        reff = FirebaseDatabase.getInstance().getReference().child("EventsList");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        options = new FirebaseRecyclerOptions.Builder<EventsList>().setQuery(reff,EventsList.class).build();
        adapter = new FirebaseRecyclerAdapter<EventsList, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull EventsList model) {
                holder.eventName.setText(""+model.getEventName());
                holder.eventDate.setText(""+model.getEventDate());
                holder.eventTime.setText(""+model.getEventTime());
                holder.eventDesc.setText(""+model.getEventDesc());

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_layout,parent, false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    private void configureNext() {
        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Event_Page.this, createE.class));
            }
        });
    }

}