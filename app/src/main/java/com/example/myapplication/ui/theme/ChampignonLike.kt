package com.example.myapplication.ui.theme


import android.media.effect.EffectUpdateListener
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.ChampignonViewModel


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampignonLike(viewModel: ChampignonViewModel) {
    val champignonslike by viewModel.champignonFavori.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getChampignonlike()
    }
    //EffectUpdateListener()
    Column(
        modifier = Modifier
            .padding(0.dp, 70.dp)
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            itemsIndexed(champignonslike) { _, champignonlike ->

                if (!champignonlike.img.isNullOrEmpty()) {
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
                                model = champignonlike.img,
                                contentDescription = champignonlike.name,
                                modifier = Modifier.fillMaxWidth()
                            )

                            Text(text = champignonlike.name ?: "Sans nom")

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            ) {
                                Text(text = champignonlike.type ?: "Type non trouvé")

                                var like by remember { mutableStateOf(false) }


                                DislikeChampignon(
                                    like = like,
                                    onLikeChange = { like = it },
                                    champignonlike,
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
@Composable
fun DislikeChampignon(
    like: Boolean,
    onLikeChange: (Boolean) -> Unit,champignonlike: ChampignonEntity, viewModel: ChampignonViewModel
) {
    Row(modifier = Modifier.padding(10.dp)) {
        IconButton(onClick = { onLikeChange(!like) }) {
            if (like) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null
                )

            } else {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
                viewModel.suppChampignon(champignonlike.name)
            }
        }
    }
}