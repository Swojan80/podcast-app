package com.example.posdcastshow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val discoverButton: Button = findViewById(R.id.discoverButton)
        val usernameEditText: EditText = findViewById(R.id.emailInput)
        val passwordEditText: EditText = findViewById(R.id.passwordInput)

        discoverButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login(username: String, password: String) {
        val baseUrl = "https://nit3213-api-h2b3-latest.onrender.com"
        val endpoint = "/sydney/auth"

        val requestBody = JSONObject().apply {
            put("username", username)
            put("password", password)
        }

        val jsonMediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body = requestBody.toString().toRequestBody(jsonMediaType)

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(baseUrl + endpoint)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonResponse = JSONObject(responseBody)
                    val keyPass = jsonResponse.getString("keypass")

                    // Store keyPass in Shared Preferences
                    val sharedPref = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("KEYPASS", keyPass)
                        apply()
                    }

                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@LoginActivity, "Invalid username or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
