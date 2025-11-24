package com.example.myapplication.repository

import androidx.room.Database
import androidx.room.RoomDatabase

// Permet de créer la base de donnée avec la classe ChampignonEntity
@Database(entities = [ChampignonEntity::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun champignonDao(): ChampignonDao
}