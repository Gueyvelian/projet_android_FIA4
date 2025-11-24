package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.theme.Champignon
import com.example.myapplication.ui.theme.ChampignonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class ChampignonViewModel(application: Application) : AndroidViewModel(application) {
    val repository = ChampignonRepository(application)
    val champignons = MutableStateFlow<List<Champignon>>(emptyList())
    val champignonFavori = MutableStateFlow<List<ChampignonEntity>>(emptyList())
    val texteRecherche = mutableStateOf("")


    fun getChampignon() {
        viewModelScope.launch {
            // --- DÉBUT DE LA CORRECTION ---
            try {
                // On essaie de faire l'appel réseau
                champignons.value = repository.listeChampignons()
                Log.d("ViewModel-Success", "Champignons chargés avec succès.")
            } catch (e: Exception) {
                // Si une erreur (n'importe quelle exception) se produit...
                Log.e("ViewModel-Error", "Impossible de charger les champignons: ${e.message}")
                // On s'assure que la liste est vide pour ne pas afficher d'anciennes données
                champignons.value = emptyList()
                // Ici, vous pourriez aussi mettre à jour un autre StateFlow pour afficher un message d'erreur à l'utilisateur
            }
            // --- FIN DE LA CORRECTION ---
        } }

            /**
     * Appelé pour mettre à jour la recherche.
     * @param nouveauTexte Le texte entré par l'utilisateur.
     */
    fun texteRecherche(nouveauTexte: String) {
        texteRecherche.value = nouveauTexte
        // Lance la recherche à chaque modification du texte
        rechercherChampignons(nouveauTexte)
    }

    /**
     * Lance la recherche dans le Repository et met à jour le StateFlow `_champignons`.
     * @param nom Le nom du champignon à rechercher.
     */
    fun rechercherChampignons(nom: String) {
        viewModelScope.launch {
            // Appelle la fonction du repository qui filtre la liste
            val resultatRecherche = repository.getChampignonRecherche(nom)
            // Met à jour la liste des champignons qui sera affichée dans Cueillir.kt
            champignons.value = resultatRecherche
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
    fun suppChampignonLike(champignon: Champignon) {
        viewModelScope.launch {
            repository.deleteChampignon(champignon.name)
            Log.v("lucie", "sippChampignon ${champignon.like}")
            getChampignonlike()
        }

    }
}