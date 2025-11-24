package com.example.myapplication.model

import com.example.myapplication.repository.ChampignonEntity
import kotlinx.serialization.Serializable

// Permet de récupérer des objets identiques à ceux de l'api
@Serializable
data class Champignon(
    val name: String,
    val commonname: String? = null,
    val agent: String? = null,
    val img: String? = null,
    val type: String? = null,
    var like: Boolean = false
){
    fun toChampignonEntity(): ChampignonEntity {
        return ChampignonEntity(name, commonname, agent, img, type, like)
    }
}