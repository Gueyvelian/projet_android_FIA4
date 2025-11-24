package com.example.myapplication.pages


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
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.myapplication.ChampignonViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import com.example.myapplication.repository.ChampignonEntity


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampignonLike(viewModel: ChampignonViewModel) {
    val champignonslike by viewModel.champignonFavori.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChampignonlike()
    }

    if (champignonslike.isEmpty()) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Vous n'avez pas encore de favoris",
                style = MaterialTheme.typography.bodyLarge, // Un style un peu plus discret
                textAlign = TextAlign.Center,
                color = Color.White // Couleur du texte en blanc
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(champignonslike, key = { it.name }) { champignon ->
                ChampignonFavoriCard(
                    champignonEntity = champignon,
                    viewModel = viewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampignonFavoriCard(champignonEntity: ChampignonEntity, viewModel: ChampignonViewModel) {
    if (champignonEntity.img.isNullOrEmpty()) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column {
            GlideImage(
                model = champignonEntity.img,
                contentDescription = champignonEntity.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = champignonEntity.name ?: "Sans nom",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = champignonEntity.commonname ?: "Nom commun non trouvé",
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
                        text = champignonEntity.type ?: "Type non trouvé",
                        style = MaterialTheme.typography.labelMedium
                    )
                    DislikeButton(
                        champignonEntity = champignonEntity,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
fun DislikeButton(champignonEntity: ChampignonEntity, viewModel: ChampignonViewModel) {
    IconButton(onClick = { viewModel.suppChampignonLike(champignonEntity.toChampignon()) }) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Supprimer des favoris",
            // Utilise la couleur d'erreur du thème pour une action destructive
            tint = MaterialTheme.colorScheme.error
        )
    }
}