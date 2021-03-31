package com.example.maverickandskills;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class allusersFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    List<Modeluser> userList;

    //firebase auth
    FirebaseAuth firebaseAuth;


    public allusersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_allusers, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        //init recycle view
        recyclerView = view.findViewById(R.id.alluserrecyclid);
        // set its properties

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //init user list
        userList = new ArrayList<>();
        // get all users

        getAllusers();
        

        return view;
    }

    private void getAllusers()
    {
        // get current user

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        // get the path of data base named users

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

                // get all data from path

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Modeluser modeluser = ds.getValue(Modeluser.class);
                    // get all users except current user
                    if(!modeluser.getPhone().equals(fuser.getPhoneNumber()))
                    {
                        userList.add(modeluser);
                    }
                    //Adapters

                    adapterUsers =  new AdapterUsers(getActivity(),userList);

                    // Adapter settings to recycle view
                    recyclerView.setAdapter(adapterUsers);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void searchusers(final String query)
    {
        // get current user

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        // get the path of data base named users

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        // get all data from path

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Modeluser modeluser = ds.getValue(Modeluser.class);
                    // get all users except current user
                    if(!modeluser.getPhone().equals(fuser.getPhoneNumber()))
                    {
                        if(modeluser.getName().toLowerCase().contains(query.toLowerCase()) ||
                              modeluser.getSkills().toLowerCase().contains(query.toLowerCase()))
                        {
                            userList.add(modeluser);
                        }

                    }
                    //Adapters

                    adapterUsers =  new AdapterUsers(getActivity(),userList);

                    // refresh adapter
                    adapterUsers.notifyDataSetChanged();

                    // Adapter settings to recycle view
                    recyclerView.setAdapter(adapterUsers);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


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
            startActivity(new Intent(getActivity(),MainActivity.class));
            getActivity().finish();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    //inflate options menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflating menu
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        // search listener

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // called when user press search button
                if(!TextUtils.isEmpty(query.trim()))
                {
                    // search text contains , search it
                    searchusers(query);
                }
                else
                {
                    getAllusers();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                // called when user press any single letter
                // called when user press search button
                if(!TextUtils.isEmpty(newText.trim()))
                {
                    // search text contains , search it
                    searchusers(newText);
                }
                else
                {
                    getAllusers();
                }
                return false;
            }
        });


        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get item id
        int id = item.getItemId();
//        if(id==R.id.action_logout)
//        {
//            firebaseAuth.signOut();
//            checkUserStatus();
//        }
        return super.onOptionsItemSelected(item);
    }
}
//Model class for recyclerView.
