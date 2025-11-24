package com.example.myapplication.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ChampignonEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun champignonDao(): ChampignonDao
}