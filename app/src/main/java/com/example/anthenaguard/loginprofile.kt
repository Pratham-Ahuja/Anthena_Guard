package com.example.anthenaguard

import com.example.anthenaguard.TutorialActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anthenaguard.Safety_beacon

class loginprofile : AppCompatActivity() {

    private lateinit var bluetoothManager: BluetoothManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginprofile) // Change this line to set the correct layout file

        Toast.makeText(this, R.string.welcome_message, Toast.LENGTH_SHORT).show()

        bluetoothManager = BluetoothManager(this)
        bluetoothManager.startAdvertising()

        setupButtons()
    }

    private fun setupButtons() {
        val sosButton = findViewById<Button>(R.id.sos_button)
        val communityButton = findViewById<Button>(R.id.community_button)
        val tutorialButton = findViewById<Button>(R.id.tutorial_button)
        val profileButton = findViewById<Button>(R.id.profile_button)
        val safetyButton = findViewById<Button>(R.id.Safety_Beacon)

        sosButton.setOnClickListener { startSOSActivity() }
        profileButton.setOnClickListener { startViewProfileActivity() }
        communityButton.setOnClickListener { startCommunityActivity() }
        tutorialButton.setOnClickListener { startTutorialActivity() }
        safetyButton.setOnClickListener { startSafetyBeaconActivity() }
    }

    private fun startSOSActivity() {
        startActivity(Intent(this, SOSActivity::class.java))
    }

    private fun startViewProfileActivity() {
        startActivity(Intent(this, ViewProfileActivity::class.java))
    }

    private fun startCommunityActivity() {
        startActivity(Intent(this, CommunityActivity::class.java))
    }

    private fun startTutorialActivity() {
        startActivity(Intent(this, TutorialActivity::class.java))
    }

    private fun startSafetyBeaconActivity() {
        startActivity(Intent(this, Safety_beacon::class.java))
    }
}