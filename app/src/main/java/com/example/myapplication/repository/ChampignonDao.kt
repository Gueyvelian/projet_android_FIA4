package com.example.myapplication.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChampignonDao {

    // Permet de récupérer la liste des champignon liké dans la base de donnée
    @Query("SELECT * FROM champignonentity")
    suspend fun getFavChampignon(): List<ChampignonEntity>

    // Permet d'ajouter un champignon liké dans la base de donnée
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertChampignon(champignon: ChampignonEntity)

    // Permet de suprimer un champignon liké de la base de donnée
    @Query("DELETE FROM champignonentity WHERE name = :name")
    suspend fun deleteChampignon(name: String)

    // Permet de savoir si un champignon est liké ou non
    @Query("SELECT * FROM champignonentity WHERE name = :name")
    suspend fun champignonLikeRoom(name: String): ChampignonEntity?


    companion object
}