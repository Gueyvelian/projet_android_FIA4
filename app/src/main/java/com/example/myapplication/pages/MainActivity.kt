package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import com.example.myapplication.R


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
        // Définition du squellette de l'aplication
        MyApplicationTheme {
            val backStack = remember { mutableStateListOf<Any>(ScreenDest()) }
            val titresDesPages = when (backStack.last()) {
                is ScreenDest -> "Les champignons"
                is ChampignonLikeDest -> "Vos favoris"
                is CueillirDest -> "Découvrir"
                else -> ""
            }
            Scaffold(
                // Définition de la topBar et de BottomBar
                topBar = {
                    TopAppBar(
                        title = { Text(titresDesPages, fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            // L'icône de retour ne s'affiche que si on n'est pas sur l'écran d'accueil
                            if (backStack.size > 1) {
                                IconButton(onClick = { backStack.removeLastOrNull() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = "Retour"
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.surface,
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    // L'image en arrière-plan présente sur toute l'application
                    Image(
                        painter = painterResource(id = R.drawable.fondnoir),
                        contentDescription = "Fond d'écran forêt",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Le contenu de la navigation
                    NavDisplay(
                        backStack = backStack,
                        entryProvider = entryProvider {
                            entry<ScreenDest> {
                                Screen(
                                    { backStack.add(ChampignonLikeDest()) },
                                    { backStack.add(CueillirDest()) })
                            }
                            entry<ChampignonLikeDest> { ChampignonLike(viewModel()) }
                            entry<CueillirDest> { Cueillir(viewModel(), backStack) }
                        }
                    )
                }
            }
        }
    }

// Permet d'avoir sur la page d'accueil les composants voulu (2 boutons)
    @Composable
    fun Screen(onClickFavoris: () -> Unit, onClickCueillir: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ImageButton(
                text = "Découvrir",
                imageResId = R.drawable.champignonrecherche,
                onClick = onClickCueillir
            )

            Spacer(modifier = Modifier.height(30.dp))

            ImageButton(
                text = "Favoris",
                imageResId = R.drawable.champignonlike,
                onClick = onClickFavoris
            )
        }
    }

    // Permet de créer les deux boutons sur la page d'accueil
    @Composable
    fun ImageButton(text: String, imageResId: Int, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clickable(onClick = onClick),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = text,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        shadow = Shadow(
                            color = Color.Black.copy(alpha = 0.7f),
                            offset = Offset(4f, 4f),
                            blurRadius = 8f
                        )
                    )
                )
            }
        }
    }
}




