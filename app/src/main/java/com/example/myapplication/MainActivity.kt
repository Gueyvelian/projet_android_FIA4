package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.theme.ChampignonLike
import com.example.myapplication.ui.theme.Cueillir
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Home // J'ai vu que vous utilisiez AccountCircle, Home est plus standard
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.unit.sp


class ScreenDest()
class ChampignonLikeDest()
class CueillirDest()
class MainActivity : ComponentActivity() {
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "acceuil") {
                composable("acceuil") {
                    Main()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main() {
        MyApplicationTheme {
            val backStack = remember { mutableStateListOf<Any>(ScreenDest()) }
            val titresDesPages = when (backStack.last()) {
                is ScreenDest -> "Les champignons" // Titre simplifié
                is ChampignonLikeDest -> "Vos favoris"
                is CueillirDest -> "Découvrir"
                else -> ""
            }
            Scaffold(
                topBar = {
                    // --- MODIFICATION DE LA TOPAPPBAR ---
                    TopAppBar(
                        title = { Text(titresDesPages, fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            // L'icône de retour ne s'affiche que si on n'est pas sur l'écran d'accueil
                            if (backStack.size > 1) {
                                IconButton(onClick = { backStack.removeLastOrNull() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack, // Icône de retour standard
                                        contentDescription = "Retour"
                                    )
                                }
                            }
                        },
                        // On applique les mêmes couleurs que la BottomAppBar pour la cohérence
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface, // Même couleur que la BottomBar
                            titleContentColor = MaterialTheme.colorScheme.onSurface,
                            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        // Icône Découvrir
                        NavigationBarItem(
                            selected = backStack.last() is CueillirDest,
                            onClick = {
                                backStack.removeIf { it !is ScreenDest }
                                backStack.add(CueillirDest())
                            },
                            icon = { Icon(Icons.Default.Search, contentDescription = "Découvrir") },
                            label = { Text("Découvrir") }
                        )
                        // Icône Accueil
                        NavigationBarItem(
                            selected = backStack.last() is ScreenDest,
                            onClick = {
                                backStack.clear()
                                backStack.add(ScreenDest())
                            },
                            // J'utilise l'icône Home, plus standard pour l'accueil
                            icon = { Icon(Icons.Default.Home, contentDescription = "Accueil") },
                            label = { Text("Accueil") }
                        )

                        // Icône Favoris
                        NavigationBarItem(
                            selected = backStack.last() is ChampignonLikeDest,
                            onClick = {
                                backStack.removeIf { it !is ScreenDest }
                                backStack.add(ChampignonLikeDest())
                            },
                            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoris") },
                            label = { Text("Favoris") }
                        )
                    }
                },

                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                // --- DÉBUT DE LA MODIFICATION ---
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding) // Applique le padding du Scaffold
                ) {
                    // 1. L'image en arrière-plan
                    Image(
                        painter = painterResource(id = R.drawable.fondnoir),
                        contentDescription = "Fond d'écran forêt",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop // Remplit l'espace sans déformer
                    )

                    // 2. Le contenu de la navigation par-dessus
                    NavDisplay(
                        backStack = backStack,
                        entryProvider = entryProvider {
                            entry<ScreenDest> {
                                Screen(
                                    { backStack.add(ChampignonLikeDest()) },
                                    { backStack.add(CueillirDest()) })
                            }
                            entry<ChampignonLikeDest> { ChampignonLike(viewModel()) }
                            // J'ai mis à jour l'appel à Cueillir pour être cohérent avec le nouveau style
                            entry<CueillirDest> { Cueillir(viewModel(), backStack) }
                        }
                    )
                }
                // --- FIN DE LA MODIFICATION ---
            }
        }
    }


    // NOUVELLE VERSION DE VOTRE ÉCRAN D'ACCUEIL
    @Composable
    fun Screen(onClickFavoris: () -> Unit, onClickCueillir: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Ajoute de la marge autour de la colonne
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centre les boutons verticalement
        ) {
            // Premier bouton-image "Découvrir"
            ImageButton(
                text = "Découvrir",
                imageResId = R.drawable.champignonrecherche, // Votre image pour la recherche
                onClick = onClickCueillir
            )

            // Espace entre les deux boutons
            Spacer(modifier = Modifier.height(30.dp)) // J'ai un peu réduit l'espace pour l'équilibre

            // Deuxième bouton-image "Favoris"
            ImageButton(
                text = "Favoris",
                imageResId = R.drawable.champignonlike, // Votre image pour les favoris
                onClick = onClickFavoris
            )
        }
    }

    // COMPOSABLE RÉUTILISABLE POUR LES BOUTONS-IMAGES
    @Composable
    fun ImageButton(text: String, imageResId: Int, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                // --- C'EST LA LIGNE À MODIFIER ---
                .height(220.dp) // On augmente la hauteur, par exemple de 150.dp à 220.dp
                .clickable(onClick = onClick), // Rend toute la carte cliquable
            shape = MaterialTheme.shapes.large, // Coins arrondis
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Ombre portée
        ) {
            // Le Box permet de superposer l'image et le texte
            Box(contentAlignment = Alignment.Center) {
                // 1. L'image de fond
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = text, // Pour l'accessibilité
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // L'image remplit l'espace sans se déformer
                )

                // 2. Le texte par-dessus
                Text(
                    text = text,
                    color = Color.White, // Texte en blanc pour être lisible
                    fontSize = 32.sp, // J'ai aussi légèrement augmenté la taille du texte
                    fontWeight = FontWeight.Bold, // Police en gras
                    style = MaterialTheme.typography.headlineMedium.copy(
                        // Ajoute une ombre au texte pour améliorer la lisibilité
                        shadow = androidx.compose.ui.graphics.Shadow(
                            color = Color.Black.copy(alpha = 0.7f),
                            offset = androidx.compose.ui.geometry.Offset(4f, 4f),
                            blurRadius = 8f
                        )
                    )
                )
            }
        }
    }
}




