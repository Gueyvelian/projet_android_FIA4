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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                    Main(navController = navController)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main(navController: NavController) {
        MyApplicationTheme{
            val backStack = remember { mutableStateListOf<Any>(ScreenDest()) }
            val titresDesPages = when (backStack.last()){
                is ScreenDest -> "Les champignons empoisonés"
                is ChampignonLikeDest -> "Les champignons likés"
                is CueillirDest -> "Les champignons rencontrés"
                else -> {""}
            }
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(titresDesPages)
                        },
                        navigationIcon = { if (backStack.size > 1 )
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        actions = { if (backStack.size > 1 )
                            IconButton(onClick = {backStack.removeLastOrNull() }) {
                                Icon(
                                    Icons.Default.ArrowForward,
                                    contentDescription = null
                                )
                            }
                        })
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding))
                NavDisplay(
                    backStack = backStack,
                    entryProvider = entryProvider {
                        entry<ScreenDest> { Screen({backStack.add(ChampignonLikeDest())}, {backStack.add(CueillirDest())}) }
                        entry<ChampignonLikeDest> { ChampignonLike(viewModel()) }
                        entry<CueillirDest> { Cueillir(viewModel()) }
                    }
                )

            }
        }
    }

    @Composable
    fun MiseEnPage(x0: () -> Any?) {
        TODO("Not yet implemented")
    }

}


@Composable
fun Screen(function: () -> Boolean, function1: () -> Boolean) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(R.drawable.champi),
            contentDescription = "Amanite",
            modifier = Modifier.size(250.dp)
        )
        CueillirButton(function, function1)


    }


}

@Composable
fun CueillirButton(onclickChampignonLike: () -> Boolean, onclickCueillir: () -> Boolean) {
    Row(modifier = Modifier.padding(10.dp)) {
        Button(onClick = { onclickCueillir() }) {
            Text("Cueillir")

        }
        Button(
            onClick = { onclickChampignonLike() },
            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(),
            border = androidx.compose.material3.ButtonDefaults.outlinedButtonBorder
        ) {
            Text("Champignon liké")

        }

    }

}

/*
@Composable
fun BackgroundImageExample() {
    Box(
        modifier = Modifier.fillMaxSize()  // L'image prendra toute la taille disponible
    ) {
        // Image en fond
        Image(
            painter = painterResource(id = R.drawable.mon_image),  // Remplace "mon_image" par ton image
            contentDescription = null,
            contentScale = ContentScale.Crop,  // L'image sera recadrée pour remplir l'espace
            modifier = Modifier.fillMaxSize()  // L'image occupe tout l'espace disponible dans le Box
        )

        // Autres éléments à superposer par-dessus l'image
        Text(
            text = "Texte sur le fond",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.align(Alignment.Center)  // Centrer le texte par-dessus l'image
        )
    }
}
 */