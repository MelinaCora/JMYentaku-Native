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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.jmyentaku.app.data.model.toAnime
import com.jmyentaku.app.ui.components.GeneralComponent.AnimeStatusMenu
import com.jmyentaku.app.ui.components.GeneralComponent.CustomButton
import com.jmyentaku.app.viewmodel.detail.DetailViewModel
import com.jmyentaku.app.viewmodel.home.HomeViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    id: Int,
    type: String
) {

    val viewModel: DetailViewModel = viewModel()

    val uiState =
        viewModel.uiState.collectAsState().value

    LaunchedEffect(id, type) {

        viewModel.loadDetails(id, type)
    }

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(

                brush = Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF0F172A),
                        Color(0xFF111827),
                        Color(0xFF1E1B4B)
                    )
                )
            )
    ) {

        when {

            uiState.isLoading -> {

                Box(

                    modifier = Modifier.fillMaxSize(),

                    contentAlignment = Alignment.Center
                ) {

                    CircularProgressIndicator(
                        color = Color(0xFF38BDF8)
                    )
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

                        Text(
                            text = "Error: ${uiState.error}",
                            color = Color.White
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

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

                AnimeDetailContent(

                    anime = uiState.animeDetail!!,

                    onBack = {

                        navController.popBackStack()
                    }
                )
            }

            type == "manga" && uiState.mangaDetail != null -> {

                MangaDetailContent(

                    manga = uiState.mangaDetail!!,

                    onBack = {

                        navController.popBackStack()
                    }
                )
            }

            type == "actor" && uiState.voiceActorDetail != null -> {

                VoiceActorDetailContent(

                    actor = uiState.voiceActorDetail!!,

                    onBack = {

                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun AnimeDetailContent(
    anime: AnimeDetail,
    onBack: () -> Unit
) {

    val context =
        LocalContext.current

    val homeViewModel: HomeViewModel = viewModel()

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data(anime.images.jpg.image_url)
                    .crossfade(true)
                    .build(),

                contentDescription = anime.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),

                contentScale = ContentScale.Crop
            )

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .background(

                        Brush.verticalGradient(

                            colors = listOf(

                                Color.Transparent,
                                Color(0xFF0F172A)
                            )
                        )
                    )
            )

            Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(

                    onClick = onBack,

                    modifier = Modifier.background(

                        Color.Black.copy(alpha = 0.4f),

                        shape = RoundedCornerShape(50)
                    )
                ) {

                    Icon(

                        imageVector = Icons.Default.ArrowBack,

                        contentDescription = "Back",

                        tint = Color.White
                    )
                }

                Box {

                    IconButton(

                        onClick = {

                            expanded = true
                        },

                        modifier = Modifier.background(

                            Color.Black.copy(alpha = 0.4f),

                            shape = RoundedCornerShape(50)
                        )
                    ) {

                        Icon(

                            imageVector = Icons.Default.MoreVert,

                            contentDescription = "Menu",

                            tint = Color.White
                        )
                    }

                    AnimeStatusMenu(

                        expanded = expanded,

                        onDismiss = {

                            expanded = false
                        },

                        onInProgress = {

                            expanded = false

                            homeViewModel.saveAnimeStatus(

                                anime = anime.toAnime(),

                                status = "in_progress"
                            )
                        },

                        onCompleted = {

                            expanded = false

                            homeViewModel.saveAnimeStatus(

                                anime = anime.toAnime(),

                                status = "completed"
                            )
                        },

                        onPlanned = {

                            expanded = false

                            homeViewModel.saveAnimeStatus(

                                anime = anime.toAnime(),

                                status = "planned"
                            )
                        }
                    )
                }
            }
        }

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(

                text = anime.title,

                color = Color.White,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            if (anime.title_japanese != null) {

                Text(

                    text = anime.title_japanese,

                    color = Color.LightGray,

                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF38BDF8)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                StatItem(
                    value = String.format("%.1f", anime.score ?: 0.0),
                    label = "Score"
                )

                StatItem(
                    value = "${anime.favorites}",
                    label = "Favorites"
                )

                StatItem(
                    value = "${anime.episodes ?: "?"}",
                    label = "Episodes"
                )

                StatItem(
                    value = "#${anime.rank ?: "?"}",
                    label = "Rank"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            if (!anime.genres.isNullOrEmpty()) {

                SectionTitle(
                    title = "Genres"
                )

                ChipsRow(
                    items = anime.genres.map { it.name }
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            if (!anime.studios.isNullOrEmpty()) {

                SectionTitle(
                    title = "Studios"
                )

                Text(

                    text = anime.studios.joinToString(", ") { it.name },

                    color = Color.LightGray,

                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            if (anime.synopsis != null) {

                SectionTitle(
                    title = "Synopsis"
                )

                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E293B)
                    ),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(

                        text = anime.synopsis,

                        color = Color.White,

                        fontSize = 14.sp,

                        lineHeight = 24.sp,

                        modifier = Modifier.padding(18.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )
        }
    }
}

@Composable
fun MangaDetailContent(
    manga: MangaDetail,
    onBack: () -> Unit
) {

    val context =
        LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data(manga.images.jpg.image_url)
                    .crossfade(true)
                    .build(),

                contentDescription = manga.title,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),

                contentScale = ContentScale.Crop
            )

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .background(

                        Brush.verticalGradient(

                            colors = listOf(

                                Color.Transparent,
                                Color(0xFF0F172A)
                            )
                        )
                    )
            )

            IconButton(

                onClick = onBack,

                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(50)
                    )
            ) {

                Icon(

                    imageVector = Icons.Default.ArrowBack,

                    contentDescription = "Back",

                    tint = Color.White
                )
            }
        }

        Column(

            modifier = Modifier.padding(20.dp)
        ) {

            Text(

                text = manga.title,

                color = Color.White,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF38BDF8)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                StatItem(
                    value = String.format("%.1f", manga.score ?: 0.0),
                    label = "Score"
                )

                StatItem(
                    value = "${manga.favorites}",
                    label = "Favorites"
                )

                StatItem(
                    value = "${manga.chapters ?: "?"}",
                    label = "Chapters"
                )

                StatItem(
                    value = "${manga.volumes ?: "?"}",
                    label = "Volumes"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            if (!manga.genres.isNullOrEmpty()) {

                SectionTitle(
                    title = "Genres"
                )

                ChipsRow(
                    items = manga.genres.map { it.name }
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            if (manga.synopsis != null) {

                SectionTitle(
                    title = "Synopsis"
                )

                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E293B)
                    ),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(

                        text = manga.synopsis,

                        color = Color.White,

                        fontSize = 14.sp,

                        lineHeight = 24.sp,

                        modifier = Modifier.padding(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun VoiceActorDetailContent(
    actor: VoiceActorDetail,
    onBack: () -> Unit
) {

    val context =
        LocalContext.current

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Box {

            AsyncImage(

                model = ImageRequest.Builder(context)
                    .data(actor.images.jpg.image_url)
                    .crossfade(true)
                    .build(),

                contentDescription = actor.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp),

                contentScale = ContentScale.Crop
            )

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .background(

                        Brush.verticalGradient(

                            colors = listOf(

                                Color.Transparent,
                                Color(0xFF0F172A)
                            )
                        )
                    )
            )

            IconButton(

                onClick = onBack,

                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(50)
                    )
            ) {

                Icon(

                    imageVector = Icons.Default.ArrowBack,

                    contentDescription = "Back",

                    tint = Color.White
                )
            }
        }

        Column(

            modifier = Modifier.padding(20.dp)
        ) {

            Text(

                text = actor.name,

                color = Color.White,

                fontSize = 30.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color(0xFF38BDF8)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            StatItem(
                value = "${actor.favorites}",
                label = "Favorites"
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            if (actor.about != null) {

                SectionTitle(
                    title = "About"
                )

                Card(

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E293B)
                    ),

                    shape = RoundedCornerShape(20.dp)
                ) {

                    Text(

                        text = actor.about,

                        color = Color.White,

                        fontSize = 14.sp,

                        lineHeight = 24.sp,

                        modifier = Modifier.padding(18.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun StatItem(
    value: String,
    label: String
) {

    Card(

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1E293B)
        ),

        shape = RoundedCornerShape(18.dp)
    ) {

        Column(

            modifier = Modifier
                .padding(
                    horizontal = 18.dp,
                    vertical = 14.dp
                ),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(

                text = value,

                color = Color.White,

                fontSize = 18.sp,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(

                text = label,

                color = Color.LightGray,

                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SectionTitle(
    title: String
) {

    Column {

        Text(

            text = title,

            color = Color.White,

            fontSize = 22.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFF38BDF8)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}

@Composable
fun ChipsRow(
    items: List<String>
) {

    Row(

        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items.take(5).forEach { item ->

            Surface(

                shape = RoundedCornerShape(20.dp),

                color = Color(0xFF2563EB)
            ) {

                Text(

                    text = item,

                    color = Color.White,

                    fontSize = 12.sp,

                    modifier = Modifier.padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    ),

                    maxLines = 1,

                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}