package com.example.myapplication.ui.theme

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ChampignonEntity (
    @PrimaryKey val name: String, val commonName: String ?,
    val agent: String ?, val img : String ?, val type: String ?, val like: Boolean ){
    fun toChampignon(): Champignon{
        return Champignon(name, commonName, agent, img, type, like)
    }
}

