package com.example.myapplication.ui.theme

import android.media.Image
import kotlinx.serialization.Serializable



@Serializable
data class Champignon(
    val name: String,
    val commonname: String? = null,
    val agent: String? = null,
    val img: String? = null,
    val type: String? = null,
    var like: Boolean = false
){
    fun toChampignonEntity(): ChampignonEntity{
        return ChampignonEntity(name, commonname, agent, img, type, like)
    }
}


