package com.bignerdranch.android.greetv2;

import android.os.Bundle;
//import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView num;
    Button btn1;
    Button btn2 = findViewById(R.id.addBy2);
    Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.num);
        btn1 = findViewById(R.id.addBy1);
        btn3 = findViewById(R.id.addBy3);

        // Initialize number
        num.setText("0");

        btn1.setOnClickListener(v -> {
            int current = Integer.parseInt(num.getText().toString());
            num.setText(String.valueOf(current + 1));
        });

        btn2.setOnClickListener(v -> {
            int current = Integer.parseInt(num.getText().toString());
            num.setText(String.valueOf(current + 2));
        });

        btn3.setOnClickListener(v -> {
            int current = Integer.parseInt(num.getText().toString());
            num.setText(String.valueOf(current + 3));
        });
    }
}