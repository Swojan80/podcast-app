package com.example.posdcastshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserActivity : AppCompatActivity() {
    private lateinit var recipeCollectionRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        recipeCollectionRecyclerView = findViewById(R.id.favoriteShowsLabel)
        recipeCollectionRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recipeCollectionRecyclerView = findViewById(R.id.favoriteShowsLabel)
        recipeCollectionRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Set your adapter here
        //favoriteShowsLabel = YourAdapter(yourData)
    }
}