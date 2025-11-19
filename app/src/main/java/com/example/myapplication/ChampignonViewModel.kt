package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Champignon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.listOf

class ChampignonViewModel(application: Application): AndroidViewModel(application) {
    val repository = ChampignonRepository(application)
    val champignon = MutableStateFlow<List<Champignon>>(emptyList())

    fun getChampignon() {
        viewModelScope.launch {
            champignon.value = repository.listeChampignons()
        }
    }
    /*fun getChampignonlike() {
        viewModelScope.launch {
            champignon.value = repository.getFavChampignon()
        }
    }*/
}