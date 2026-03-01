package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class VerifyOtpActivity extends AppCompatActivity {

    EditText etOtp;
    Button btnVerify;
    FirebaseAuth mAuth;

    String verificationId, phone, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);
        mAuth = FirebaseAuth.getInstance();

        verificationId = getIntent().getStringExtra("verificationId");
        phone = getIntent().getStringExtra("phone");
        role = getIntent().getStringExtra("role");

        btnVerify.setOnClickListener(v -> {

            String otp = etOtp.getText().toString().trim();

            PhoneAuthCredential credential =
                    PhoneAuthProvider.getCredential(verificationId, otp);

            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            String uid = mAuth.getCurrentUser().getUid();

                            UserModel user =
                                    new UserModel(uid, phone, role);

                            FirebaseDatabase.getInstance()
                                    .getReference("Users")
                                    .child(uid)
                                    .setValue(user);

                            if (role != null && role.equals("owner")) {
                                startActivity(new Intent(VerifyOtpActivity.this,
                                        OwnerHomeActivity.class));
                            } else {
                                startActivity(new Intent(VerifyOtpActivity.this,
                                        StudentHomeActivity.class));
                            }

                            finish();

                        } else {
                            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}