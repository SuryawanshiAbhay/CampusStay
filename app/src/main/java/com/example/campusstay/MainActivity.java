package com.example.campusstay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnStudent, btnOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return insets;
        });

        // connect buttons
        btnStudent = findViewById(R.id.btnStudent);
        btnOwner = findViewById(R.id.btnOwner);

        // open student screen
        btnStudent.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, StudentLoginActivity.class))
        );

        // open owner screen
        btnOwner.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, OwnerAddHostelActivity.class))
        );
    }
}