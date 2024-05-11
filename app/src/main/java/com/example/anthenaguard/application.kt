package com.example.anthenaguard

import android.app.Application
import com.google.firebase.FirebaseApp

class AnthenaGuard : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}