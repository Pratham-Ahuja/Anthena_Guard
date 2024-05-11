package com.example.anthenaguard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import android.media.MediaPlayer
import android.os.Vibrator
import android.telephony.SmsManager
import android.location.LocationManager
import android.content.Context
import android.location.Location
import android.Manifest
import android.content.pm.PackageManager

class SOSActivity : AppCompatActivity() {

    private lateinit var locationManager: LocationManager
    private lateinit var guardianNumber: String
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sosactivity)

        Toast.makeText(this, "SOS Activity", Toast.LENGTH_SHORT).show()

        // Retrieve the guardian number from SharedPreferences or a database
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        guardianNumber = sharedPreferences.getString("guardian_number", "")!!

        val sendSOSButton = findViewById<Button>(R.id.send_sos_button)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        sendSOSButton.setOnClickListener {
            if (checkSelfPermission(Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.ACCESS_FINE_LOCATION), 123)
            } else {
                try {
                    val location = getLocation()
                    val message = "Help! I'm in trouble. My location is: $location"

                    sendSMS(guardianNumber, message)
                    playPoliceSiren()
                    vibratePhone()
                } catch (e: SecurityException) {
                    Toast.makeText(this, "Security exception: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getLocation(): String {
        try {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            return if (location!= null) {
                "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
            } else {
                "Unable to get location"
            }
        } catch (e: SecurityException) {
            Toast.makeText(this, "Security exception: ${e.message}", Toast.LENGTH_SHORT).show()
            return "Unable to get location"
        }
    }

    private fun sendSMS(number: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(number, null, message, null, null)
            Toast.makeText(this, "SOS message sent to $number", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            Toast.makeText(this, "Security exception: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun playPoliceSiren() {
        mediaPlayer.start()
    }

    private fun vibratePhone() {
        vibrator.vibrate(1000) // Vibrate for 1 second
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            123 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    try {
                        val location = getLocation()
                        val message = "Help! I'm in trouble. My location is: $location"

                        sendSMS(guardianNumber, message)
                        playPoliceSiren()
                        vibratePhone()
                    } catch (e: SecurityException) {
                        Toast.makeText(this, "Security exception: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }
}