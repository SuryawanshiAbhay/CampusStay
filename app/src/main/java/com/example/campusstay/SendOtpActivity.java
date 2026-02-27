package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtpActivity extends AppCompatActivity {

    EditText etPhone;
    Button btnSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);

        etPhone = findViewById(R.id.etPhone);
        btnSendOtp = findViewById(R.id.btnSendOtp);

        btnSendOtp.setOnClickListener(v -> {

            String phone = etPhone.getText().toString().trim();

            // remove spaces if user pasted number
            phone = phone.replaceAll("\\s", "");

            String fullPhone;

            // if user enters +91XXXXXXXXXX
            if (phone.startsWith("+91") && phone.length() == 12) {
                fullPhone = phone;
            }
            // if user enters 10 digit number
            else if (phone.length() == 10) {
                fullPhone = "+91" + phone;
            }
            else {
                Toast.makeText(this, "Enter valid number", Toast.LENGTH_SHORT).show();
                return;
            }

            sendOtp(fullPhone);
        });
    }

    private void sendOtp(String phoneNumber) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                this,

                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                        Toast.makeText(SendOtpActivity.this,
                                "Auto Verification Completed",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(SendOtpActivity.this,
                                "Verification Failed: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {

                        Toast.makeText(SendOtpActivity.this,
                                "OTP Sent",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SendOtpActivity.this, VerifyOtpActivity.class);
                        intent.putExtra("verificationId", verificationId);
                        startActivity(intent);
                    }
                }
        );
    }
}