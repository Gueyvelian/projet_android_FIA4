package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.compose.animation.core.copy
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Champignon
import com.example.myapplication.ui.theme.ChampignonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.listOf


class ChampignonViewModel(application: Application) : AndroidViewModel(application) {
    val repository = ChampignonRepository(application)
    val champignons = MutableStateFlow<List<Champignon>>(emptyList())
    val champignonFavori = MutableStateFlow<List<ChampignonEntity>>(emptyList())


    fun getChampignon() {
        viewModelScope.launch {
            champignons.value = repository.listeChampignons()
        }
    }

    fun getChampignonlike() {
        viewModelScope.launch {
            champignonFavori.value = repository.getFavChampignon()
        }
    }

    fun suppChampignon(champignon: Champignon) {
        viewModelScope.launch {
            repository.deleteChampignon(champignon.name)
            Log.v("lucie", "sippChampignon ${champignon.like}")


        }
        champignons.value = champignons.value.map {
            if (it.name == champignon.name) {
                it.copy(like = false)
            } else {
                it
            }
        }

    }

    //aller voir pourquoi on ne peu pas le metre dans le viewmodelscope

    fun addChampignon(champignon: Champignon) {
        viewModelScope.launch {
            repository.insertChampignon(champignon)
            Log.v("lucie", "addChampignon ${champignon.like}")


        }
        champignons.value = champignons.value.map {
            if (it.name == champignon.name) {
                it.copy(like = true)
            } else {
                it
            }
        }

    }
}