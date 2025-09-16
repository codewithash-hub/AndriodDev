package com.bignerdranch.android.geoquiz;

import static com.bignerdranch.android.geoquiz.R.*;
import static com.bignerdranch.android.geoquiz.R.id.textViewResult;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText editTextData;
    Button btnSaveDB, btnSaveSP;
    TextView textViewResult;

    DatabaseHelper dbHelper;
    SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "MyPrefs";
    public static final String KEY_TEXT = "savedText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextData = findViewById(R.id.editTextData);
        btnSaveDB = findViewById(R.id.btnSaveDB);
        btnSaveSP = findViewById(R.id.btnSaveSP);
        textViewResult = findViewById(R.id.textViewResult);

        dbHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        // Save to Database
        btnSaveDB.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String text = editTextData.getText().toString();
                if (dbHelper.insertData(text)) {
                    textViewResult.setText("Saved to DB: " + dbHelper.getLastData());
                } else {
                    textViewResult.setText("Error saving to database");
                }
            }
        });

        // Save to SharedPreferences
        btnSaveSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextData.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_TEXT, text);
                editor.apply();

                String saved = sharedPreferences.getString(KEY_TEXT, "No data");
                textViewResult.setText(String.format("Saved to SharedPreferences: %s", saved));
            }
        });
    }
}
