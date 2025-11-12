package com.example.myapplication.ui.theme


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.ChampignonLike
import com.example.myapplication.ui.theme.Cueillir
import com.example.myapplication.ui.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiseEnPageIscrit(onclickOk: () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Evènements")
                },
                navigationIcon = { Icon(Icons.Default.Menu, contentDescription = null) },
                actions = { Icon(Icons.Default.Favorite, contentDescription = null) })
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        Cueillir (onclickOk)
    }

}


@Composable
fun Cueillir (onclickOk: () -> Unit){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Merci de vous être inscrit(e)")
        Button(onClick = {onclickOk()}) {
            Text("Ok")

        }

    }

}