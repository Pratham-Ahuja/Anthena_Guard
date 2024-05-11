package com.example.anthenaguard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import com.example.anthenaguard.loginprofile

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var numberEditText: EditText
    private lateinit var guardianNameEditText: EditText
    private lateinit var guardianNumberEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.name_edit_text)
        numberEditText = findViewById(R.id.number_edit_text)
        guardianNameEditText = findViewById(R.id.guardian_name_edit_text)
        guardianNumberEditText = findViewById(R.id.guardian_number_edit_text)
        registerButton = findViewById(R.id.register_button)
        loginButton = findViewById(R.id.login_button)

        registerButton.setOnClickListener {
            // Register user logic here
            Toast.makeText(this, "Register button clicked", Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            // Login user logic here
            val name = nameEditText.text.toString()
            val number = numberEditText.text.toString()
            val guardianName = guardianNameEditText.text.toString()
            val guardianNumber = guardianNumberEditText.text.toString()
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, loginprofile::class.java)
            startActivity(intent)}}}