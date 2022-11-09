package com.example.blooddonationapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.blooddonationapp.R;
import com.example.blooddonationapp.interfaces.ItemClickListener;

class UserViewHolder extends RecyclerView.ViewHolder{
    private final TextView username, phoneNumber, location, bloodGroup;

    private UserViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        username = itemView.findViewById(R.id.fullName);
        phoneNumber = itemView.findViewById(R.id.phoneNumber);
        location = itemView.findViewById(R.id.location);
        bloodGroup = itemView.findViewById(R.id.bloodGroupTxt);

        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onClick(position);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void bind(String username1, String phoneNumber1, String location1, String bloodGroup1) {
        username.setText("Username: " + username1);
        phoneNumber.setText("Phone number: " + phoneNumber1);
        location.setText("Location: " + location1);
        bloodGroup.setText("Blood group: " + bloodGroup1);
    }

    static UserViewHolder create(ViewGroup parent, ItemClickListener itemClickListener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        return new UserViewHolder(view, itemClickListener);
    }
}
