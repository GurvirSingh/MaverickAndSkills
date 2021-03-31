package com.example.maverickandskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class updateProfileNew extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ImageView avatartv;
    TextView nametv,emailtv,phonetv,skillstv;

    TextView createProName,name,createProMajor,phone,createProDOB,email,skills,createProPhoneno,createProEmail,createProStags,createProBio,uid;
    Button createProSubmit;
    Profiles prof;
    DatabaseReference reff ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_new);

        // init firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

//        avatartv = view.findViewById(R.id.avatarid);
        nametv = findViewById(R.id.createProName);
        emailtv = findViewById(R.id.createProEmail);
        phonetv = findViewById(R.id.createProDOB);
        skillstv = findViewById(R.id.createProMajor);

        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                // check until required get data
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String name = ""+ ds.child("name").getValue();
                    String email = ""+ ds.child("email").getValue();
                    String phone = ""+ ds.child("phone").getValue();
                    String skills = ""+ ds.child("skills").getValue();
                    String image = ""+ ds.child("image").getValue();

                    // set data
                    nametv.setText(name);
                    emailtv.setText(email);
                    phonetv.setText(phone);
                    skillstv.setText(skills);

                    createProSubmit = (Button)findViewById(R.id.createProSubmit);
                    prof = new Profiles();
                    reff = FirebaseDatabase.getInstance().getReference().child("Users");

                    createProSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prof.setname(nametv.getText().toString().trim());
//                prof.setCreateProMajor(createProMajor.getText().toString().trim());
//                prof.setCreateProDOB(createProDOB.getText().toString().trim());
                            prof.setphone(phonetv.getText().toString().trim());
                            prof.setemail(emailtv.getText().toString().trim());
                            prof.setskills(skillstv.getText().toString().trim());
//                prof.setCreateProBio(createProBio.getText().toString().trim());

                            reff.push().setValue(prof);
                            Toast.makeText(updateProfileNew.this ,"Profile Updated Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(updateProfileNew.this, MainActivity.class));


                        }
                    });

                    try {
//                        Picasso.get().load(image).into(avatartv);

                    }
                    catch (Exception e)
                    {
//                        Picasso.get().load(R.drawable.ic_add_img).into(avatartv);
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Intent intent = getIntent();
//
//        final String name = intent.getStringExtra("Name");
//        String date = intent.getStringExtra("Date");
//        String time = intent.getStringExtra("Time");
//        String desc = intent.getStringExtra("Desc");
//
//        TextView ename = (TextView) findViewById(R.id.name);
//        TextView edate = (TextView) findViewById(R.id.date);
//        TextView etime = (TextView) findViewById(R.id.time);
//        TextView edesc = (TextView) findViewById(R.id.desc);
//
//        ename.setText(name);
//        edate.setText(date);
//        etime.setText(time);
//        edesc.setText(desc);


    }
}
