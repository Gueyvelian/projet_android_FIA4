package com.example.myapplication

import android.util.Log
import com.example.myapplication.ui.theme.Champignon
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.serialization.kotlinx.json.json

class ChampignonRepository {
    val client = HttpClient(CIO) {

        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor-Logger", message)
                }
            }
        }

    }
    val url = "https://toxicshrooms.vercel.app/api/mushrooms"
    suspend fun listeChampignons(): List<Champignon> {
        return client.request(url) {
            method = HttpMethod.Get
        }.body()
    }
}