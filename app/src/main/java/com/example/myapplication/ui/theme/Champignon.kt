package com.example.myapplication.ui.theme

import android.media.Image
import kotlinx.serialization.Serializable



@Serializable
data class Champignon(
    val name: String,
    val commonName: String? = null,
    val agent: String? = null,
    // val distribution: List<String>? = null,
    val img: String? = null,
    val type: String? = null
){
    fun toChampignonEntity(): ChampignonEntity{
        return ChampignonEntity(name, commonName, agent, img, type)
    }
}


