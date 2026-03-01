package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class RedirectActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        String uid = mAuth.getCurrentUser().getUid();

        FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            String role = snapshot.child("role").getValue(String.class);

                            if ("owner".equalsIgnoreCase(role)) {
                                startActivity(new Intent(RedirectActivity.this,
                                        OwnerHomeActivity.class));
                            } else {
                                startActivity(new Intent(RedirectActivity.this,
                                        StudentHomeActivity.class));
                            }
                        } else {
                            startActivity(new Intent(RedirectActivity.this,
                                    MainActivity.class));
                        }

                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        finish();
                    }
                });
    }
}