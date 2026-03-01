package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StudentHomeActivity extends AppCompatActivity {

    Button btnViewHostels, btnProfile, btnLogout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        mAuth = FirebaseAuth.getInstance();

        // If user not logged in, go to Main
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        btnViewHostels = findViewById(R.id.btnViewHostels);
        btnProfile = findViewById(R.id.btnProfile);
        btnLogout = findViewById(R.id.btnLogout);

        // View Hostels (you can connect to hostel list later)
        btnViewHostels.setOnClickListener(v ->
                startActivity(new Intent(StudentHomeActivity.this,
                        HostelListActivity.class))
        );

        // Open Profile
        btnProfile.setOnClickListener(v ->
                startActivity(new Intent(StudentHomeActivity.this,
                        StudentProfileActivity.class))
        );

        // Logout
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(StudentHomeActivity.this,
                    MainActivity.class));
            finish();
        });
    }
}