package com.example.myapplication.ui.theme.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.ui.theme.repository.ChampignonEntity

@Dao
interface ChampignonDao {
    @Query("SELECT * FROM champignonentity")
    suspend fun getFavChampignon(): List<ChampignonEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertChampignon(champignon: ChampignonEntity)

    @Query("DELETE FROM champignonentity WHERE name = :name")
    suspend fun deleteChampignon(name: String)

    @Query("SELECT * FROM champignonentity WHERE name = :name")
    suspend fun champignonLikeRoom(name: String): ChampignonEntity?


    companion object
}