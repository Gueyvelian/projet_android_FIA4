package com.example.myapplication.ui.theme

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ChampignonEntity (
    @PrimaryKey val name: String,
    val agent: String)
