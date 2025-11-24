package com.example.myapplication.ui.theme.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.ui.theme.repository.ChampignonDao
import com.example.myapplication.ui.theme.repository.ChampignonEntity

@Database(entities = [ChampignonEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun champignonDao(): ChampignonDao
}