package com.example.kmmktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

class Greeting {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val format = Json { prettyPrint = true }

        val response = client.get("https://ktor.io/docs/")
        val text = response.bodyAsText()
        //format.encodeToString(text)
        return text
    }
}