package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Champignon
import com.example.myapplication.repository.ChampignonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ChampignonViewModel(application: Application) : AndroidViewModel(application) {
    val repository = ChampignonRepository(application)
    val champignons = MutableStateFlow<List<Champignon>>(emptyList())
    val champignonFavori = MutableStateFlow<List<ChampignonEntity>>(emptyList())
    val texteRecherche = mutableStateOf("")

// Permet d'afficher la liste des champignon de l'api dans la page Cueillir.kt
    fun getChampignon() {
        viewModelScope.launch {
            try {
                // On essaie de faire l'appel réseau
                champignons.value = repository.listeChampignons()
                Log.d("ViewModel-Success", "Champignons chargés avec succès.")
            } catch (e: Exception) {
                // Si une erreur (n'importe quelle exception) se produit...
                Log.e("ViewModel-Error", "Impossible de charger les champignons: ${e.message}")
                champignons.value = emptyList()
            }
        } }

// Permet de recupérer le texte de la barre de recherche dans la page Cueillir.kt
    fun texteRecherche(nouveauTexte: String) {
        texteRecherche.value = nouveauTexte
        // Lance la recherche à chaque modification du texte
        rechercherChampignons(nouveauTexte)
    }

// Permet de filtrer la liste des champignon de l'api dans la page Cueillir.kt
    fun rechercherChampignons(nom: String) {
        viewModelScope.launch {
            // Appelle la fonction du repository qui filtre la liste
            val resultatRecherche = repository.getChampignonRecherche(nom)
            // Met à jour la liste des champignons qui sera affichée dans Cueillir.kt
            champignons.value = resultatRecherche
        }
    }
// Permet d'afficher la liste des champignon liké dans la page ChampignonLike.kt
    fun getChampignonlike() {
        viewModelScope.launch {
            champignonFavori.value = repository.getFavChampignon()
        }
    }

// Permet de suprimer un champignon liké de la base de donnée depuis la page Cueillir.kt,
// et rafréchi la page ChampignonLike.kt
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

// Permet d'ajouter un champignon liké dans la base de donnée depuis la page Cueillir.kt,
// et rafréchi la page Cueillir.kt
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

// Permet de suprimer un champignon liké de la base de donnée depuis la page ChampignonLike.kt,
// et rafréchi la page ChampignonLike.kt

    fun suppChampignonLike(champignon: Champignon) {
        viewModelScope.launch {
            repository.deleteChampignon(champignon.name)
            Log.v("lucie", "sippChampignon ${champignon.like}")
            getChampignonlike()
        }

    }
}