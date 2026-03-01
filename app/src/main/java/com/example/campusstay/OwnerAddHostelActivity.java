package com.example.campusstay;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnerAddHostelActivity extends AppCompatActivity {

    EditText etHostelName, etLocation, etRent, etRooms;
    Button btnAddHostel;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_hostel);

        etHostelName = findViewById(R.id.etHostelName);
        etLocation = findViewById(R.id.etLocation);
        etRent = findViewById(R.id.etRent);
        etRooms = findViewById(R.id.etRooms);
        btnAddHostel = findViewById(R.id.btnAddHostel);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Hostels");

        btnAddHostel.setOnClickListener(v -> addHostel());
    }

    private void addHostel() {

        String hostelName = etHostelName.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String rent = etRent.getText().toString().trim();
        String rooms = etRooms.getText().toString().trim();

        if (TextUtils.isEmpty(hostelName) ||
                TextUtils.isEmpty(location) ||
                TextUtils.isEmpty(rent) ||
                TextUtils.isEmpty(rooms)) {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        String hostelId = databaseReference.push().getKey();
        String ownerId = mAuth.getCurrentUser().getUid();

        HostelModel hostelModel = new HostelModel(
                hostelId,
                ownerId,
                hostelName,
                location,
                rent,
                rooms
        );

        databaseReference.child(hostelId).setValue(hostelModel)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Hostel Added Successfully", Toast.LENGTH_SHORT).show();

                    etHostelName.setText("");
                    etLocation.setText("");
                    etRent.setText("");
                    etRooms.setText("");
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }
}