package com.example.posdcastshow

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Get data passed from the intent
        val name = intent.getStringExtra("EXTRA_NAME")
        val culture = intent.getStringExtra("EXTRA_CULTURE")
        val domain = intent.getStringExtra("EXTRA_DOMAIN")
        val description = intent.getStringExtra("EXTRA_DESCRIPTION")
        val symbol = intent.getStringExtra("EXTRA_SYMBOL")
        val parentage = intent.getStringExtra("EXTRA_PARENTAGE")
        val romanEquivalent = intent.getStringExtra("EXTRA_ROMAN_EQUIVALENT")

        // Set up TextViews with the data
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val cultureTextView: TextView = findViewById(R.id.cultureTextView)
        val domainTextView: TextView = findViewById(R.id.domainTextView)
        val descriptionTextView: TextView = findViewById(R.id.descriptionTextView)
        val symbolTextView: TextView = findViewById(R.id.symbolTextView)
        val parentageTextView: TextView = findViewById(R.id.parentageTextView)
        val romanEquivalentTextView: TextView = findViewById(R.id.romanEquivalentTextView)

        // Bind the values to the TextViews
        nameTextView.text = name
        cultureTextView.text = "Culture: $culture"
        domainTextView.text = "Domain: $domain"
        symbolTextView.text = "Symbol: $symbol"
        parentageTextView.text = "Parentage: $parentage"
        romanEquivalentTextView.text = "Roman Equivalent: $romanEquivalent"
        descriptionTextView.text = description
    }
}
