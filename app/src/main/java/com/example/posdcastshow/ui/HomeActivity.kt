package com.example.posdcastshow

import Entity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class HomeActivity : AppCompatActivity() {
    private lateinit var trendingEpisodesList: RecyclerView
    private lateinit var newReleasesList: RecyclerView
    private lateinit var trendingAdapter: EntityAdapter
    private lateinit var newReleasesAdapter: EntityAdapter
    private val trendingEntities = mutableListOf<Entity>()
    private val newReleaseEntities = mutableListOf<Entity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Initialize RecyclerViews
        trendingEpisodesList = findViewById(R.id.trendingEpisodesList)
        newReleasesList = findViewById(R.id.newReleasesList)

        // Set layout managers
        trendingEpisodesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        newReleasesList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Initialize adapters
        trendingAdapter = EntityAdapter(trendingEntities)
        newReleasesAdapter = EntityAdapter(newReleaseEntities)

        // Set adapters to RecyclerViews
        trendingEpisodesList.adapter = trendingAdapter
        newReleasesList.adapter = newReleasesAdapter

        // Fetch data and populate RecyclerView
        fetchDashboardData()
    }

    private fun fetchDashboardData() {
        val baseUrl = "https://nit3213-api-h2b3-latest.onrender.com"
        // Retrieve keyPass from SharedPreferences
        val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val keyPass = sharedPref.getString("KEYPASS", null) // Default value is null if keyPass doesn't exist


        val client = OkHttpClient()
        val request = Request.Builder()
            .url("$baseUrl/dashboard/$keyPass")
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@HomeActivity, "Failed to fetch dashboard data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonResponse = JSONObject(responseBody)
                    val entitiesArray = jsonResponse.getJSONArray("entities")

                    runOnUiThread {
                        parseEntities(entitiesArray)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@HomeActivity, "Error fetching dashboard data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun parseEntities(entitiesArray: JSONArray) {
        for (i in 0 until entitiesArray.length()) {
            val entityObject = entitiesArray.getJSONObject(i)
            val entity = Entity(
                name = entityObject.getString("name"),
                culture = entityObject.getString("culture"),
                domain = entityObject.getString("domain"),
                symbol = entityObject.getString("symbol"),
                parentage = entityObject.getString("parentage"),
                romanEquivalent = entityObject.getString("romanEquivalent"),
                description = entityObject.getString("description")
            )

            // For simplicity, adding all entities to both lists, but you can customize logic here
            trendingEntities.add(entity)
            newReleaseEntities.add(entity)
        }

        // Notify the adapters about the new data
        trendingAdapter.notifyDataSetChanged()
        newReleasesAdapter.notifyDataSetChanged()
    }
}
