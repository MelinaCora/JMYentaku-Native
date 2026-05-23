package com.jmyentaku.app.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            uiState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
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
            type == "anime" && uiState.animeDetail != null -> {
                AnimeDetailContent(anime = uiState.animeDetail)
            }
            type == "manga" && uiState.mangaDetail != null -> {
                MangaDetailContent(manga = uiState.mangaDetail)
            }
            type == "actor" && uiState.voiceActorDetail != null -> {
                VoiceActorDetailContent(actor = uiState.voiceActorDetail)
            }
        }
    }
}

@Composable
fun AnimeDetailContent(anime: AnimeDetail) {
    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = anime.images.jpg.image_url,
            contentDescription = anime.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = anime.title, fontSize = 24.sp)
        if (anime.title_japanese != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = anime.title_japanese, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "⭐ Score: ${anime.score ?: "N/A"}")
        Text(text = "❤️ Favorites: ${anime.favorites}")
        if (anime.episodes != null) Text(text = "📺 Episodes: ${anime.episodes}")
        if (anime.status != null) Text(text = "📌 Status: ${anime.status}")
        if (anime.genres != null && anime.genres.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            val genresText = anime.genres.joinToString(", ") { it.name }
            Text(text = "🎭 Genres: $genresText")
        }
        if (anime.synopsis != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "📖 Synopsis:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = anime.synopsis)
        }
    }
}

@Composable
fun MangaDetailContent(manga: MangaDetail) {
    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = manga.images.jpg.image_url,
            contentDescription = manga.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = manga.title, fontSize = 24.sp)
        if (manga.title_japanese != null) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = manga.title_japanese, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "⭐ Score: ${manga.score ?: "N/A"}")
        Text(text = "❤️ Favorites: ${manga.favorites}")
        if (manga.chapters != null) Text(text = "📚 Chapters: ${manga.chapters}")
        if (manga.volumes != null) Text(text = "📖 Volumes: ${manga.volumes}")
        if (manga.status != null) Text(text = "📌 Status: ${manga.status}")
        if (manga.genres != null && manga.genres.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            val genresText = manga.genres.joinToString(", ") { it.name }
            Text(text = "🎭 Genres: $genresText")
        }
        if (manga.synopsis != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "📖 Synopsis:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = manga.synopsis)
        }
    }
}

@Composable
fun VoiceActorDetailContent(actor: VoiceActorDetail) {
    Column(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = actor.images.jpg.image_url,
            contentDescription = actor.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = actor.name, fontSize = 24.sp)
        if (actor.given_name != null || actor.family_name != null) {
            Spacer(modifier = Modifier.height(4.dp))
            val fullName = listOfNotNull(actor.given_name, actor.family_name).joinToString(" ")
            Text(text = fullName, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "❤️ Favorites: ${actor.favorites}")
        if (actor.about != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "📖 About:")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = actor.about)
        }
    }
}