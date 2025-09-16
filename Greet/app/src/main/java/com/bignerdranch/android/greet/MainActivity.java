package com.bignerdranch.android.greet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText greet;
    TextView txtDisplay = findViewById(R.id.txtDisplay);
    Button btnGreet;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        greet = findViewById(R.id.greet);
        btnGreet = findViewById(R.id.btnGreet);

        btnGreet.setOnClickListener(v -> {
            String name = greet.getText().toString().trim();
            if (!name.isEmpty()) {
               txtDisplay.setText(String.format("Hello %s", name));
               greet.setText("");
            } else {
                txtDisplay.setText("Please enter your name.");
                greet.setText("");
                greet.setFocusable(true);
            }
        });
    }
}