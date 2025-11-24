package com.example.myapplication.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.ChampignonViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.model.Champignon

// Page qui permet d'afficher les champignons de l'api et de les mettre en favoris
// et/ou de rechercher un champignon
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Cueillir(viewModel: ChampignonViewModel, backStack: MutableList<Any>) {
    val champignons by viewModel.champignons.collectAsState()
    val texteRecherche by viewModel.texteRecherche
    LaunchedEffect(Unit) {
        viewModel.getChampignon()
    }

    // Permet de occupe du nettoyage de la bar de recherche lorsque l'on quitte l'écran
    DisposableEffect(Unit) {
        onDispose {
            viewModel.texteRecherche("")
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 10.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espace vertical entre les cartes.
    ) {
        // Ajoute une ligne avec un champ de recherche
        stickyHeader {
            OutlinedTextField(
                value = texteRecherche,
                onValueChange = { viewModel.texteRecherche(it) },
                label = { Text("Rechercher un champignon...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedTextColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                singleLine = true
            )
        }
        // Si aucun champignon n'est trouvé, on affiche un message
        if (champignons.isEmpty() && texteRecherche.isNotEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillParentMaxSize()
                        .padding(top = 100.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "Aucun résultat trouvé",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
        // Sinon on affiche les champignon trouvé
        else {
            items(items = champignons, key = { it.name }) { champignon ->
                ChampignonCard(
                    champignon = champignon,
                    viewModel = viewModel
                )
            }
        }
    }
}

// Gère l'affichage des champignons
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampignonCard(champignon: Champignon, viewModel: ChampignonViewModel) {
    // Nous affichons que les champignons qui ont une image
    if (champignon.img.isNullOrEmpty()) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        border = BorderStroke(1.dp, Color.White),

    ) {
        Column {
            GlideImage(
                model = champignon.img,
                contentDescription = champignon.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = champignon.name ?: "Sans nom",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = champignon.commonname ?: "Nom commun non trouvé",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = champignon.type ?: "Type non trouvé",
                        style = MaterialTheme.typography.labelMedium
                    )

                    LikeButton(
                        champignon = champignon,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

// Gere les like des chanpignons sur la page découvrir et permet le changement de couleur de l'icon "ceur"
@Composable
fun LikeButton(champignon: Champignon, viewModel: ChampignonViewModel) {
    IconButton(onClick = {
        if (!champignon.like) {
            viewModel.addChampignon(champignon)
        } else {
            viewModel.suppChampignon(champignon)
        }
    }) {
        if (champignon.like) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Ne plus aimer",
                tint = MaterialTheme.colorScheme.error
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Aimer",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
