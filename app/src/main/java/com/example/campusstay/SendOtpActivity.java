package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {

    EditText etPhone;
    Button btnSendOtp;
    FirebaseAuth mAuth;
    String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        etPhone = findViewById(R.id.etPhone);
        btnSendOtp = findViewById(R.id.btnSendOtp);
        mAuth = FirebaseAuth.getInstance();

        role = getIntent().getStringExtra("role");

        btnSendOtp.setOnClickListener(v -> {
            String inputPhone = etPhone.getText().toString().trim();

            if (inputPhone.length() != 10) {
                Toast.makeText(this, "Enter valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            final String phone = "+91" + inputPhone;

            PhoneAuthOptions options =
                    PhoneAuthOptions.newBuilder(mAuth)
                            .setPhoneNumber(phone)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential credential) {
                                }

                                @Override
                                public void onVerificationFailed(com.google.firebase.FirebaseException e) {
                                    Toast.makeText(SendOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(String verificationId,
                                                       PhoneAuthProvider.ForceResendingToken token) {

                                    Intent intent = new Intent(SendOtpActivity.this, VerifyOtpActivity.class);
                                    intent.putExtra("verificationId", verificationId);
                                    intent.putExtra("phone", phone);
                                    intent.putExtra("role", role);
                                    startActivity(intent);
                                }
                            })
                            .build();

            PhoneAuthProvider.verifyPhoneNumber(options);
        });
    }
}