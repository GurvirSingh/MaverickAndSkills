package com.example.maverickandskills;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileActivity extends AppCompatActivity
{
    //firebase auth
    FirebaseAuth firebaseAuth;
    ActionBar actionBar;

    //TextView myprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        firebaseAuth = FirebaseAuth.getInstance();

        BottomNavigationView navigationView = findViewById(R.id.bottomnav);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

//         myprofile = findViewById(R.id.profileid);

    }

    private  BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
        {

            // handle item clicks

            switch(menuItem.getItemId())
            {
                case R.id.nav_home:
                    // profile fragment transaction
                    actionBar.setTitle("Profile");
                    profileFragment fragment1 = new profileFragment();
                    FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                    ft1.replace(R.id.content, fragment1, "");
                    ft1.commit();
                    return true;

                case R.id.all_users:
                    // profile fragment transaction
                    actionBar.setTitle("All Users");
                    allusersFragment fragment4 = new allusersFragment();
                    FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                    ft4.replace(R.id.content, fragment4, "");
                    ft4.commit();
                    return true;

//                case R.id.nav_event:
//                    // events fragment transaction
//                    actionBar.setTitle("Events");
//                    EventsFragment fragment2 = new EventsFragment();
//                    FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                    ft2.replace(R.id.content, fragment2,"");
//                    ft2.commit();
//                    return true;
//
//                case R.id.nav_sett:
//                    // settings fragment transaction
//                    actionBar.setTitle("Settings");
//                    settingsFragment fragment3 = new settingsFragment();
//                    FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
//                    ft3.replace(R.id.content, fragment3, "");
//                    ft3.commit();
//                    return true;
            }
            return false;
        }

    };

    private void checkUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null)
        {
            //user is signed in
            //  myprofile.setText(user.getEmail());

        }
        else
        {
            //user mot signed go to main Activity
            startActivity(new Intent(profileActivity.this,MainActivity.class));
            finish();
        }
    }

    @Override
    // check on stat of the app
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

}
