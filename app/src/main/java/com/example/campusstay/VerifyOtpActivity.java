package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOtpActivity extends AppCompatActivity {

    EditText etOtp;
    Button btnVerify;

    String verificationId;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        etOtp = findViewById(R.id.etOtp);
        btnVerify = findViewById(R.id.btnVerify);

        mAuth = FirebaseAuth.getInstance();

        verificationId = getIntent().getStringExtra("verificationId");

        btnVerify.setOnClickListener(v -> {
            String code = etOtp.getText().toString();
            verifyCode(code);
        });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(verificationId, code);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(this, MainActivity.class));
                        finish();
                    }
                });
    }
}