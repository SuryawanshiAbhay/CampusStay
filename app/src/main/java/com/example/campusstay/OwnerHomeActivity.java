package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class OwnerHomeActivity extends AppCompatActivity {

    Button btnAddHostel, btnProfile, btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        mAuth = FirebaseAuth.getInstance();

        // If user not logged in, go to Main
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        btnAddHostel = findViewById(R.id.btnAddHostel);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // Add Hostel
        btnAddHostel.setOnClickListener(v ->
                startActivity(new Intent(OwnerHomeActivity.this,
                        OwnerAddHostelActivity.class))
        );

        // Open Profile
        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(OwnerHomeActivity.this,
                        OwnerProfileActivity.class))
        );

        // Logout
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(OwnerHomeActivity.this,
                    MainActivity.class));
            finish();
        });
    }
}