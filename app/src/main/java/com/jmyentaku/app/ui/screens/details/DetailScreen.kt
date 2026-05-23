package com.jmyentaku.app.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jmyentaku.app.data.model.AnimeDetail
import com.jmyentaku.app.data.model.MangaDetail
import com.jmyentaku.app.data.model.VoiceActorDetail
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.viewmodel.detail.DetailViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    type: String
) {
    val viewModel: DetailViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(id, type) {
        viewModel.loadDetails(id, type)
    }

    when {
        uiState.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        uiState.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error: ${uiState.error}")
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomButton(
                        text = "Go Back",
                        onClick = {
                            viewModel.clearState()
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
        type == "anime" && uiState.animeDetail != null -> {
            AnimeDetailContent(anime = uiState.animeDetail!!)
        }
        type == "manga" && uiState.mangaDetail != null -> {
            MangaDetailContent(manga = uiState.mangaDetail!!)
        }
        type == "actor" && uiState.voiceActorDetail != null -> {
            VoiceActorDetailContent(actor = uiState.voiceActorDetail!!)
        }
    }
}

@Composable
fun AnimeDetailContent(anime: AnimeDetail) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(anime.images.jpg.image_url)
                    .crossfade(true)
                    .build(),
                contentDescription = anime.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 200.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface
                            )
                        )
                    )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = anime.title,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            if (anime.title_japanese != null) {
                Text(
                    text = anime.title_japanese,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (anime.title_english != null && anime.title_english != anime.title) {
                Text(
                    text = anime.title_english,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                StatItem(value = String.format("%.1f", anime.score ?: 0.0), label = "Score", icon = "⭐")
                StatItem(value = "${anime.favorites}", label = "Favorites", icon = "❤️")
                StatItem(value = "${anime.episodes ?: "?"}", label = "Episodes", icon = "📺")
                StatItem(value = "${anime.rank ?: "?"}", label = "Rank", icon = "🏆")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (anime.status != null) {
                    InfoChip(text = anime.status, icon = "📌")
                }
                if (anime.season != null) {
                    InfoChip(text = "${anime.season.capitalize()} ${anime.year ?: ""}", icon = "📅")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (anime.rating != null) {
                InfoChip(text = anime.rating, icon = "🔞")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (!anime.genres.isNullOrEmpty()) {
                SectionTitle(title = "Genres")
                ChipsRow(items = anime.genres.map { it.name })
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (!anime.studios.isNullOrEmpty()) {
                SectionTitle(title = "Studios")
                Text(
                    text = anime.studios.joinToString(", ") { it.name },
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            if (anime.synopsis != null) {
                SectionTitle(title = "Synopsis")
                Text(
                    text = anime.synopsis,
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String, icon: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = icon, fontSize = 24.sp)
        Text(text = value, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
    }
}

@Composable
fun InfoChip(text: String, icon: String) {
    Surface(
        modifier = Modifier.padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = icon, fontSize = 12.sp)
            Text(text = text, fontSize = 12.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ChipsRow(items: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.take(5).forEach { item ->
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.secondaryContainer
            ) {
                Text(
                    text = item,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MangaDetailContent(manga: MangaDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = manga.images.jpg.image_url,
            contentDescription = manga.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = manga.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        if (manga.title_japanese != null) {
            Text(
                text = manga.title_japanese,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItem(value = String.format("%.1f", manga.score ?: 0.0), label = "Score", icon = "⭐")
            StatItem(value = "${manga.favorites}", label = "Favorites", icon = "❤️")
            StatItem(value = "${manga.chapters ?: "?"}", label = "Chapters", icon = "📚")
            StatItem(value = "${manga.volumes ?: "?"}", label = "Volumes", icon = "📖")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (manga.status != null) {
            InfoChip(text = manga.status, icon = "📌")
        }

        if (!manga.genres.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(title = "Genres")
            ChipsRow(items = manga.genres.map { it.name })
        }

        if (manga.synopsis != null) {
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(title = "Synopsis")
            Text(text = manga.synopsis, fontSize = 14.sp, lineHeight = 22.sp)
        }
    }
}

@Composable
fun VoiceActorDetailContent(actor: VoiceActorDetail) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AsyncImage(
            model = actor.images.jpg.image_url,
            contentDescription = actor.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = actor.name, fontSize = 28.sp, fontWeight = FontWeight.Bold)

        if (actor.given_name != null || actor.family_name != null) {
            val fullName = listOfNotNull(actor.given_name, actor.family_name).joinToString(" ")
            Text(
                text = fullName,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StatItem(value = "${actor.favorites}", label = "Favorites", icon = "❤️")

        if (actor.about != null) {
            Spacer(modifier = Modifier.height(16.dp))
            SectionTitle(title = "About")
            Text(text = actor.about, fontSize = 14.sp, lineHeight = 22.sp)
        }
    }
}