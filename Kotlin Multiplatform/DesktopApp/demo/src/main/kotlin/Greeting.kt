package com.example.kmmktor

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Serializable
data class Stt(
    val status: String
)

class Greeting {
    private val client = HttpClient()

    suspend fun greeting(): String {
        val format = Json { prettyPrint = true }

        val response = client.get("http://3.39.54.196/health")
        val text = response.bodyAsText()
        // val body = format.ec
         val body: Stt = Json.decodeFromString<Stt>(text)
        format.encodeToString(text)

        //val result = Json.encodeToString(text)


        return body.status
    }
}
