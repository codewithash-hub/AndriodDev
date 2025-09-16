package com.firstapp.datastorageapp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firstapp.datastorageapp.R.id
import com.firstapp.datastorageapp.R.layout

import com.firstapp.datastorageapp.R.*
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    var editTextData: EditText? = null
    var btnSaveDB: Button? = null
    var btnSaveSP: Button? = null
    var textViewResult: TextView? = null

    var dbHelper: DatabaseHelper? = null
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        editTextData = findViewById<EditText?>(id.editTextData)
        btnSaveDB = findViewById<Button?>(id.btnSaveDB)
        btnSaveSP = findViewById<Button?>(id.btnSaveSP)
        textViewResult = findViewById<TextView?>(id.textViewResults)

        dbHelper = DatabaseHelper(this)
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)

        // Save to Database
        btnSaveDB!!.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(v: View?) {
                val text = editTextData!!.getText().toString()
                if (dbHelper!!.insertData(text)) {
                    textViewResult!!.setText("Saved to DB: " + dbHelper!!.getLastData())
                } else {
                    textViewResult!!.setText("Error saving to database")
                }
            }
        })

        // Save to SharedPreferences
        btnSaveSP!!.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(v: View?) {
                val text = editTextData!!.getText().toString()
                sharedPreferences!!.edit {
                    putString(KEY_TEXT, text)
                }

                val saved: String = sharedPreferences!!.getString(KEY_TEXT, "No data")!!
                textViewResult!!.setText("Saved to SharedPreferences: " + saved)
            }
        })
    }

    companion object {
        const val PREF_NAME: String = "MyPrefs"
        const val KEY_TEXT: String = "savedText"
    }
}