package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Champignon
import com.example.myapplication.ui.theme.ChampignonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.listOf


class ChampignonViewModel(application: Application): AndroidViewModel(application) {
    val repository = ChampignonRepository(application)
    val champignon = MutableStateFlow<List<Champignon>>(emptyList())
    val champignonFavori = MutableStateFlow<List<ChampignonEntity>>(emptyList())


    fun getChampignon() {
        viewModelScope.launch {
            champignon.value = repository.listeChampignons()
        }
    }
    fun getChampignonlike() {
        viewModelScope.launch {
            champignonFavori.value = repository.getFavChampignon()
        }
    }

    fun suppChampignon(name : String) {
        viewModelScope.launch {
            repository.deleteChampignon(name)
            getChampignonlike()
        }
    }

    fun addChampignon(champignonAdd: Champignon) {
        viewModelScope.launch {
            repository.insertChampignon(champignonAdd)
            getChampignonlike()
        }
    }
}