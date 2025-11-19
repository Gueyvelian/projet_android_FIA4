package com.example.myapplication.ui.theme

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChampignonEntity::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun champignonDao(): ChampignonDao
}