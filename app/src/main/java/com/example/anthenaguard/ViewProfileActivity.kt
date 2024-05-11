package com.example.anthenaguard
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.anthenaguard.R

class ViewProfileActivity : AppCompatActivity() {

    private lateinit var userNameTextView: TextView
    private lateinit var userNumberTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var guardiansSpinner: Spinner
    private lateinit var guardianNameEditText: EditText
    private lateinit var guardianNumberEditText: EditText
    private lateinit var addGuardianButton: Button
    private lateinit var editGuardianButton: Button

    private var guardiansList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_profile)

        userNameTextView = findViewById(R.id.userNameTextView)
        userNumberTextView = findViewById(R.id.userNumberTextView)
        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)
        guardiansSpinner = findViewById(R.id.guardiansSpinner)
        guardianNameEditText = findViewById(R.id.guardianNameEditText)
        guardianNumberEditText = findViewById(R.id.guardianNumberEditText)
        addGuardianButton = findViewById(R.id.addGuardianButton)
        editGuardianButton = findViewById(R.id.editGuardianButton)

        // Get the user's name, email, username, and usernumber from the intent extras
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val username = intent.getStringExtra("username")
        val usernumber = intent.getStringExtra("usernumber")

        // Set the text views with the user's name, email, username, and usernumber
        nameTextView.text = name
        emailTextView.text = email
        userNameTextView.text = "Username: $username"
        userNumberTextView.text = "User Number: $usernumber"

        // Populate the guardians spinner with the initial list of guardians
        guardiansList.add("No guardians yet")
        guardiansSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, guardiansList)
        guardiansSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position == 0) {
                    guardianNameEditText.setText("")
                    guardianNumberEditText.setText("")
                } else {
                    val guardian = guardiansList[position]
                    val splitGuardian = guardian.split(" ")
                    guardianNameEditText.setText(splitGuardian[0])
                    guardianNumberEditText.setText(splitGuardian[1])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        // Set up the add guardian button click listener
        addGuardianButton.setOnClickListener {
            val guardianName = guardianNameEditText.text.toString()
            val guardianNumber = guardianNumberEditText.text.toString()
            if (guardianName.isNotEmpty() && guardianNumber.isNotEmpty()) {
                guardiansList.add("$guardianName $guardianNumber")
                guardiansSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, guardiansList)
            }
        }

        // Set up the edit guardian button click listener
        editGuardianButton.setOnClickListener {
            val selectedGuardianPosition = guardiansSpinner.selectedItemPosition
            if (selectedGuardianPosition > 0) {
                val guardianName = guardianNameEditText.text.toString()
                val guardianNumber = guardianNumberEditText.text.toString()
                if (guardianName.isNotEmpty() && guardianNumber.isNotEmpty()) {
                    guardiansList[selectedGuardianPosition] = "$guardianName $guardianNumber"
                    guardiansSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, guardiansList)
                }
            }
        }
    }
}