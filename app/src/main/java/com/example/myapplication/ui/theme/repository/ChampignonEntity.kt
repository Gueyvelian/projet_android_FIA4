package com.example.myapplication.ui.theme.repository

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.ui.theme.model.Champignon

@Entity
data class ChampignonEntity (
    @PrimaryKey val name: String, val commonname: String ?,
    val agent: String ?, val img : String ?, val type: String ?, val like: Boolean ){
    fun toChampignon(): Champignon {
        return Champignon(name, commonname, agent, img, type, like)
    }
}