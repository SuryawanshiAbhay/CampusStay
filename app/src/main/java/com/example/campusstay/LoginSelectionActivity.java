package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSelectionActivity extends AppCompatActivity {

    Button btnOwner, btnStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);

        btnOwner = findViewById(R.id.btnOwner);
        btnStudent = findViewById(R.id.btnStudent);

        btnOwner.setOnClickListener(v -> {
            Intent intent = new Intent(this, SendOtpActivity.class);
            intent.putExtra("role", "owner");
            startActivity(intent);
        });

        btnStudent.setOnClickListener(v -> {
            Intent intent = new Intent(this, SendOtpActivity.class);
            intent.putExtra("role", "student");
            startActivity(intent);
        });
    }
}