package com.example.anthenaguard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class CommunityActivity : AppCompatActivity() {

    private lateinit var communityChatTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community)

        // Find the TextView
        communityChatTextView = findViewById(R.id.communityChatTextView)

        // Fetch news from the URL
        Thread {
            try {
                val url = URL("https://news.google.com/search?q=women&hl=en-IN&gl=IN&ceid=IN%3Aen")
                val inputStream = url.openConnection().getInputStream()
                val reader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null
                val news = StringBuilder()
                while ({ line = reader.readLine(); line }()!= null) {
                    news.append(line)
                }

                // Display the news in the UI
                runOnUiThread {
                    communityChatTextView.text = news.toString()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    communityChatTextView.text = "Error: ${e.message}"
                }
            }
        }.start()
    }
}