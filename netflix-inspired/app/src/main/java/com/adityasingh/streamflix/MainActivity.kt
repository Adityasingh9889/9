package com.adityasingh.streamflix

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Show(val title: String, val meta: String, val description: String)

class MainActivity : AppCompatActivity() {
    private val shows = listOf(
        Show("Cyber City", "2026 • 16+ • Sci-Fi", "A hacker discovers a hidden network controlling an entire city."),
        Show("Cloud Nine", "2026 • 13+ • Drama", "Three engineers race to save a cloud platform during a massive outage."),
        Show("Dark Protocol", "2025 • 18+ • Thriller", "An ethical hacker follows a trail of clues through a mysterious system."),
        Show("Last Packet", "2025 • 16+ • Action", "A network engineer has one night to deliver a critical packet.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val catalog = findViewById<LinearLayout>(R.id.catalog)
        shows.forEach { show ->
            val card = TextView(this).apply {
                text = "${show.title}\n${show.meta}"
                textSize = 18f
                setTextColor(0xFFFFFFFF.toInt())
                setPadding(18, 22, 18, 22)
                setBackgroundColor(0xFF181818.toInt())
                setOnClickListener { showDetails(show) }
            }
            val params = LinearLayout.LayoutParams(-1, -2)
            params.setMargins(0, 8, 0, 8)
            catalog.addView(card, params)
        }
        findViewById<Button>(R.id.play).setOnClickListener { showDetails(shows.first()) }
    }

    private fun showDetails(show: Show) {
        AlertDialog.Builder(this)
            .setTitle(show.title)
            .setMessage("${show.meta}\n\n${show.description}\n\nDemo player ready — add your own licensed video URL or local media.")
            .setPositiveButton("OK", null)
            .show()
    }
}
