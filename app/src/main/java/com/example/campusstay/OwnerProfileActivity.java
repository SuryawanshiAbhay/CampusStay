package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class OwnerProfileActivity extends AppCompatActivity {

    TextView tvPhone, tvRole;
    Button btnLogout;

    FirebaseAuth mAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        tvPhone = findViewById(R.id.tvPhone);
        tvRole = findViewById(R.id.tvRole);
        btnLogout = findViewById(R.id.btnLogoutProfile);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        String uid = mAuth.getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String phone = snapshot.child("phone").getValue(String.class);
                String role = snapshot.child("role").getValue(String.class);

                tvPhone.setText("Phone: " + phone);
                tvRole.setText("Role: " + role);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }
}
