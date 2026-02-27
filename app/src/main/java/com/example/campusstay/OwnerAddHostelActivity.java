package com.example.campusstay;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerAddHostelActivity extends AppCompatActivity {

    EditText etHostelName, etLocation, etRent, etRooms;
    Button btnSaveHostel;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_hostel);

        // connect views
        etHostelName = findViewById(R.id.etHostelName);
        etLocation = findViewById(R.id.etLocation);
        etRent = findViewById(R.id.etRent);
        etRooms = findViewById(R.id.etRooms);
        btnSaveHostel = findViewById(R.id.btnSaveHostel);

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Hostels");

        btnSaveHostel.setOnClickListener(v -> saveHostel());
    }

    private void saveHostel() {

        String hostelName = etHostelName.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String rent = etRent.getText().toString().trim();
        String rooms = etRooms.getText().toString().trim();

        if (hostelName.isEmpty() || location.isEmpty() || rent.isEmpty() || rooms.isEmpty()) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
            return;
        }

        String hostelId = databaseReference.push().getKey();

        HostelModel hostel = new HostelModel(
                hostelName, location, rent, rooms
        );

        databaseReference.child(hostelId).setValue(hostel)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Hostel Added Successfully", Toast.LENGTH_SHORT).show();

                    // clear fields
                    etHostelName.setText("");
                    etLocation.setText("");
                    etRent.setText("");
                    etRooms.setText("");
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed to Add", Toast.LENGTH_SHORT).show()
                );
    }
}