package com.example.maverickandskills;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewNoteActivity extends AppCompatActivity
{

    private EditText edit_text_title;
    private EditText edit_text_date;
    private EditText edit_text_time;
    private EditText edit_text_desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Create Event");;

        edit_text_title= findViewById(R.id.edit_text_title);
        edit_text_date= findViewById(R.id.edit_text_date);
        edit_text_time= findViewById(R.id.edit_text_time);
        edit_text_desc= findViewById(R.id.edit_text_desc);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default: return super.onOptionsItemSelected(item);

        }


    }

    private void saveNote(){

        String title = edit_text_title.getText().toString();
        String date = edit_text_date.getText().toString();
        String time = edit_text_time.getText().toString();
        String description = edit_text_desc.getText().toString();

        if(title.trim().isEmpty() || description.trim().isEmpty() || date.trim().isEmpty() || time.trim().isEmpty()){
            Toast.makeText(this, "Please Enter Some Value",Toast.LENGTH_LONG).show();
            return;
        }

        CollectionReference notebookref = FirebaseFirestore.getInstance().collection("Notebook");
        notebookref.add(new Note(title,description,date,time));
        Toast.makeText(this, "Event Created",Toast.LENGTH_LONG).show();
        finish();


    }


}
