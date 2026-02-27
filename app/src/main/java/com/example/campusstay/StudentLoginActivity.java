package com.example.campusstay;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentLoginActivity extends AppCompatActivity {

    EditText etPhone;
    Button btnSendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        etPhone = findViewById(R.id.etPhone);
        btnSendOtp = findViewById(R.id.btnSendOtp);

        btnSendOtp.setOnClickListener(v -> {

            String phone = etPhone.getText().toString();

            if (phone.length() != 10) {
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "OTP Sent (Demo)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}