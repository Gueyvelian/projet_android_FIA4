package com.example.myapplication.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import com.example.myapplication.ChampignonLikeDest

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Cueillir(viewModel: ChampignonViewModel, backStack: MutableList<Any>) {
    val champignons by viewModel.champignons.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getChampignon()
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),

        // Ajoute un padding global à la liste pour qu'elle ne colle pas aux bords de l'écran.
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espace vertical entre les cartes.
    ) {
        items(items = champignons, key = { it.name }) { champignon ->
            ChampignonCard(
                champignon = champignon,
                viewModel = viewModel
            )
        }
    }
}



@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChampignonCard(champignon: Champignon, viewModel: ChampignonViewModel) {
    // La condition est maintenant ici pour ne pas créer une carte vide.
    if (champignon.img.isNullOrEmpty()) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large, // Un peu plus arrondi.
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Ajoute une ombre légère.
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant // Utilise une couleur du thème.
        )
    ) {
        Column {
            GlideImage(
                model = champignon.img,
                contentDescription = champignon.name,
                // L'image remplit la largeur et a une hauteur fixe pour un meilleur rendu.
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    // Arrondit seulement les coins supérieurs de l'image.
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop // Assure que l'image remplit l'espace sans se déformer.
            )

            // Contenu textuel de la carte
            Column(modifier = Modifier.padding(16.dp)) {
                // Le nom du champignon est mis en avant.
                Text(
                    text = champignon.name ?: "Sans nom",
                    style = MaterialTheme.typography.titleLarge // Style plus grand pour le titre.
                )

                // Espaceur pour une meilleure séparation visuelle.
                Spacer(modifier = Modifier.height(4.dp))

                // Utilisation d'un style plus discret pour les informations secondaires.
                Text(
                    text = champignon.commonname ?: "Nom commun non trouvé",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Utilisation d'une Row pour aligner le type et le bouton de like.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Pousse les éléments aux extrémités.
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
                imageVector = Icons.Default.Favorite, // Icône pleine pour "liké"
                contentDescription = "Ne plus aimer",
                tint = MaterialTheme.colorScheme.error // Couleur rouge du thème pour indiquer une action importante.
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder, // Icône vide pour "non liké"
                contentDescription = "Aimer",
                tint = MaterialTheme.colorScheme.onSurfaceVariant // Couleur discrète.
            )
        }
    }
}
