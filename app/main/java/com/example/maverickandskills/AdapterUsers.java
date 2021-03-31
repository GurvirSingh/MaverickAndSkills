package com.example.maverickandskills;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUsers extends  RecyclerView.Adapter<AdapterUsers.Myholder>
{
    Context context;
    List<Modeluser> userList;

    public AdapterUsers(Context context, List<Modeluser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout (row.user.xml)

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_users, viewGroup, false);

        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder myholder, int i) {
        String usrimg = userList.get(i).getImage();
        final String eemail = userList.get(i).getEmail();
        String uname = userList.get(i).getName();
        String usphone= userList.get(i).getPhone();
        String mskill = userList.get(i).getSkills();

        myholder.mname.setText(uname);
        myholder.memail.setText(eemail);
        myholder.mphone.setText(usphone);
        myholder.mskills.setText(mskill);

        try{

//            Picasso.get().load(usrimg).placeholder(R.drawable.ic_photo).into(myholder.mavatar);
        }
        catch (Exception e)
        {

        }

        myholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+eemail, Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    // view holder class

    class Myholder extends RecyclerView.ViewHolder
    {
        ImageView mavatar;
        TextView mname , memail, mphone, mskills ;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            // init view
//            mavatar = itemView.findViewById(R.id.avatarid);
            mname = itemView.findViewById(R.id.nametv);
            memail = itemView.findViewById(R.id.emailid);
            mphone = itemView.findViewById(R.id.phoneid);
            mskills = itemView.findViewById(R.id.skillid);


        }
    }
}
