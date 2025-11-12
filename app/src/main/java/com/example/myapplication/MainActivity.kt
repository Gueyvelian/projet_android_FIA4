package com.example.myapplication

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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
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
            //val backStack = remember { mutableStateListOf<Any>(ScreenDest()) }
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("The poisonous mushroom")
                        },
                        navigationIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        actions = { Icon(Icons.Default.Favorite, contentDescription = null) })
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding))
                Screen()
                //NavDisplay(
                //backStack = backStack,
                //entryProvider = entryProvider {
                //    entry<ScreenDest> { Screen({backStack.add(DesoleDest())}, {backStack.add(InscriptionDest())}) }
                //    entry<DesoleDest> { Desole({backStack.removeLastOrNull() }) }
                //    entry<InscriptionDest> { Inscription({backStack.removeLastOrNull() }) }
                //}
                //)

            }
        }
    }

    @Composable
    fun ScreenDest() {
        TODO("Not yet implemented")
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}*/

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}*/

@Composable
fun Screen(/*onclick: () -> Unit, onclickIns: () -> Unit*/) {

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
        /*Column(){
            Text("Où : Ecole ingénieur ISIS")
            Text("Quand : 24 octobre")
        }*/
        InscriptionButton(/*onclick, onclickIns*/)


    }


}

@Composable
fun InscriptionButton(/*onclick: () -> Unit, onclickIns: () -> Unit*/) {
    Row(modifier = Modifier.padding(10.dp)) {
        Button(onClick = { /*onclickIns()*/ }) {
            Text("Cueillir")

        }
        Button(
            onClick = { /*onclick()*/ },
            colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(),
            border = androidx.compose.material3.ButtonDefaults.outlinedButtonBorder
        ) {
            Text("Champignon liké")

        }

    }

}