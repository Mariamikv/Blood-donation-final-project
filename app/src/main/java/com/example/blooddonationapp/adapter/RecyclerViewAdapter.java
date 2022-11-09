package com.example.blooddonationapp.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.blooddonationapp.interfaces.ItemClickListener;
import com.example.blooddonationapp.model.UserInfo;

public class RecyclerViewAdapter extends ListAdapter<UserInfo, UserViewHolder> {
    private final ItemClickListener itemClickListener;

    public RecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<UserInfo> diffCallback, ItemClickListener itemClickListener) {
        super(diffCallback);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return UserViewHolder.create(parent, itemClickListener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        UserInfo current = getItem(position);
        holder.bind(
                current.getUsername(),
                current.getPhoneNumber(),
                current.getLocation(),
                current.getBloodGroup()
        );
    }

    public static class UserDiff extends DiffUtil.ItemCallback<UserInfo> {

        @Override
        public boolean areItemsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserInfo oldItem, @NonNull UserInfo newItem) {
            return oldItem.getUsername().equals(newItem.getUsername());
        }
    }
}

