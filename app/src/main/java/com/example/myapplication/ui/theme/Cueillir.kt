package com.example.myapplication.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.ChampignonViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Cueillir(onclickOk: () -> Unit, viewModel: ChampignonViewModel) {
    val champignons by viewModel.champignon.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getChampignon()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 90.dp)
    ) {
        Button(onClick = {onclickOk()}) {
            Text("Retour")

        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            itemsIndexed(champignons) { index, champignon ->

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(12.dp)) {
                    if(!champignon.img.isNullOrEmpty())
                    {
                        Text(text = champignon.name ?: "Sans nom")
                        Text(text = champignon.type ?: "type non trouvé")
                        // Utilisation de glide a la place de coil car wikimedia ne fonctionne pas avec coil
                        GlideImage(
                            model =  champignon.img,
                            contentDescription = champignon.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                //.height(500.dp)
                                //.width(500.dp)
                        )
                        var like by remember { mutableStateOf(false) }

                        LikeChampignon(like = like, onLikeChange = { like = it })
                    }
                }
            }
        }
    }
}


@Composable
fun LikeChampignon(
    like: Boolean,
    onLikeChange: (Boolean) -> Unit
) {
    Row(modifier = Modifier.padding(10.dp)) {
        IconButton(onClick = { onLikeChange(!like) }) {
            if (like) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            } else {
                Icon(Icons.Default.Favorite, contentDescription = null)
            }
        }
    }
}
