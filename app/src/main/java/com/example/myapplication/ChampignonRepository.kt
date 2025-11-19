package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import com.example.myapplication.ui.theme.AppDatabase
import com.example.myapplication.ui.theme.Champignon
import com.example.myapplication.ui.theme.ChampignonEntity
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

class ChampignonRepository(application : Application) {
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

    val database = Room.databaseBuilder( application, AppDatabase::class.java, "database-name" )
        .fallbackToDestructiveMigration()
        .build()
    val dao = database.champignonDao()

    suspend fun getFavChampignon(): List<ChampignonEntity>{
        val listChampignoLike = dao.getFavChampignon()
        return listChampignoLike
    }

    suspend fun insertChampignon(champignon: Champignon){
        val likeUnChampignon = dao.insertChampignon(champignon.toChampignonEntity())
        return likeUnChampignon
    }

    suspend fun deleteChampignon(name: String){
        val suprChampignon = dao.deleteChampignon(name)
        return suprChampignon

    }


}