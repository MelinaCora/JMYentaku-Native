package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.jmyentaku.app.viewmodel.search.SearchViewModel

private data class SearchResult(
    val id: Int,
    val type: String,
    val title: String,
    val imageUrl: String,
    val typeLabel: String
)

@Composable
fun SearchResultsDropdown(
    query: String,
    onResultClick: (Int, String) -> Unit
) {
    val viewModel: SearchViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(query) {
        if (query.length >= 2) {
            viewModel.search(query)
        }
    }

    if (query.length < 2) return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 400.dp)
            .shadow(elevation = 8.dp)
            .background(Color(0xFF1E293B), RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    ) {
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF38BDF8), modifier = Modifier.size(32.dp))
                }
            }
            else -> {
                val results = mutableListOf<SearchResult>()

                uiState.animes.take(5).forEach { anime ->
                    results.add(SearchResult(anime.mal_id, "anime", anime.title, anime.images.jpg.image_url, "Anime"))
                }
                uiState.mangas.take(5).forEach { manga ->
                    results.add(SearchResult(manga.mal_id, "manga", manga.title, manga.images.jpg.image_url, "Manga"))
                }
                uiState.voiceActors.take(3).forEach { actor ->
                    results.add(SearchResult(actor.mal_id, "actor", actor.name, actor.images.jpg.image_url, "Actor"))
                }

                if (results.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(80.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No results for \"$query\"", color = Color.Gray, fontSize = 14.sp)
                    }
                } else {
                    LazyColumn {
                        items(results) { result ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onResultClick(result.id, result.type) }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = result.imageUrl,
                                    contentDescription = result.title,
                                    modifier = Modifier
                                        .size(45.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = result.title,
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = result.typeLabel,
                                        color = Color(0xFF38BDF8),
                                        fontSize = 11.sp
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0xFF334155))
                            )
                        }
                    }
                }
            }
        }
    }
}