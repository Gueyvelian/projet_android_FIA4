package com.example.myapplication.ui.theme


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.ChampignonViewModel
import org.jetbrains.annotations.Async


@Composable
fun Cueillir(onclickOk: () -> Unit, viewModel: ChampignonViewModel) {
    val champignons by viewModel.champignon.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getChampignon()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(champignons) { champignon ->

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)) {

                    Text(text = champignon.name ?: "Sans nom")
                    Text(text = champignon.type ?: "type non trouvé")
                        AsyncImage(
                            model = champignon.img,
                            contentDescription = champignon.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )

                }
            }

        }
        /*Text("Les champi de l'api")
        Button(onClick = { onclickOk() }) {
            Text("Ok")

        }*/
    }
}

