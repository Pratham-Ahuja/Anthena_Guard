package com.example.anthenaguard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Safety_beacon : AppCompatActivity() {

    private lateinit var bluetoothManager: BluetoothManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bluetoothManager = BluetoothManager(this)

        // Start advertising when activity starts
        bluetoothManager.startAdvertising()
    }

    override fun onDestroy() {
        super.onDestroy()

        // Stop advertising when activity is destroyed
        bluetoothManager.stopAdvertising()
    }
}