package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Champignon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.listOf

class ChampignonViewModel: ViewModel() {
    val repository = ChampignonRepository()
    val champignon = MutableStateFlow<List<Champignon>>(emptyList())

    fun getChampignon() {
        viewModelScope.launch {
            champignon.value = repository.listeChampignons()
        }
    }
}