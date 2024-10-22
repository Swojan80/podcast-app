package com.example.podcastshow.network

import okhttp3.*
import java.io.IOException

class ApiClient {
    private val client = OkHttpClient()

    fun getRequest(url: String, callback: Callback) {
        val request = Request.Builder().url(url).get().build()
        client.newCall(request).enqueue(callback)
    }

    fun postRequest(url: String, requestBody: RequestBody, callback: Callback) {
        val request = Request.Builder().url(url).post(requestBody).build()
        client.newCall(request).enqueue(callback)
    }
}
