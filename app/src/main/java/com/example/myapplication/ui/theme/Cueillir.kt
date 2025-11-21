package com.example.myapplication.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.ChampignonViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.key


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Cueillir(viewModel: ChampignonViewModel) {
    val champignons by viewModel.champignons.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChampignon()
    }
    Column(
        modifier = Modifier
            .padding(0.dp, 70.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(champignons, key = { it.name }) { champignon ->

                if (!champignon.img.isNullOrEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFD8CFC4) // Vert clair
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            GlideImage(
                                model = champignon.img,
                                contentDescription = champignon.name,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(text = champignon.name ?: "Sans nom")

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                Text(text = champignon.type ?: "Type non trouvé")

                                LikeChampignon(
                                    champignon,
                                    viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

//mettre le like dans le champi

@Composable
fun LikeChampignon(
    champignon: Champignon, viewModel: ChampignonViewModel
) {
    Row(modifier = Modifier.padding(10.dp)) {
        IconButton(onClick = {
            if (!champignon.like) {
                viewModel.addChampignon(champignon)
            } else {
                viewModel.suppChampignon(champignon)
            }
        }) {
            if (champignon.like) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )


            } else {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null
                )

            }
        }
    }
}
