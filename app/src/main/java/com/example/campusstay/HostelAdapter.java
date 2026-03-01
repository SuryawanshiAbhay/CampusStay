package com.example.campusstay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HostelAdapter extends RecyclerView.Adapter<HostelAdapter.ViewHolder> {

    Context context;
    ArrayList<HostelModel> list;

    public HostelAdapter(Context context, ArrayList<HostelModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_hostel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HostelModel hostel = list.get(position);

        holder.tvName.setText(hostel.getHostelName());
        holder.tvLocation.setText(hostel.getLocation());
        holder.tvRent.setText("₹ " + hostel.getRent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvLocation, tvRent;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvRent = itemView.findViewById(R.id.tvRent);
        }
    }
}