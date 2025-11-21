package com.example.myapplication.ui.theme

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChampignonDao {
    @Query("SELECT * FROM champignonentity")
    suspend fun getFavChampignon(): List<ChampignonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChampignon(champignon: ChampignonEntity)

    @Query("DELETE FROM champignonentity WHERE name = :name")
    suspend fun deleteChampignon(name: String)

    @Query("SELECT * FROM champignonentity WHERE name = :name")
    suspend fun champignonLikeRoom(name: String): ChampignonEntity ?


    companion object
}