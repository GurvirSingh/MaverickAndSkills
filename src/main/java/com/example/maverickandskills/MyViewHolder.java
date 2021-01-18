package com.example.maverickandskills;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyViewHolder extends RecyclerView.ViewHolder {

    TextView eventName,eventDate,eventTime,eventDesc;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        eventName = itemView.findViewById(R.id.eName);
        eventDate = itemView.findViewById(R.id.eDate);
        eventTime = itemView.findViewById(R.id.eTime);
        eventDesc = itemView.findViewById(R.id.eDesc);

    

    }
}
