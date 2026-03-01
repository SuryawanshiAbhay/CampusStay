package com.example.campusstay;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HostelListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HostelAdapter adapter;
    ArrayList<HostelModel> hostelList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hostelList = new ArrayList<>();

        adapter = new HostelAdapter(this, hostelList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Hostels");

        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                hostelList.clear();
                for (var snapshot : task.getResult().getChildren()) {
                    HostelModel hostel = snapshot.getValue(HostelModel.class);
                    hostelList.add(hostel);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}