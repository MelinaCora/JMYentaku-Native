package com.jmyentaku.app.ui.components.GeneralComponent

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jmyentaku.app.data.model.Anime
//imports para listas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.Icons
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jmyentaku.app.viewmodel.home.HomeViewModel
import androidx.compose.ui.Alignment

@Composable
fun AnimeCard(
    anime: Anime,
    onClick: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {

    var expanded by remember {

        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .width(220.dp)
            .height(420.dp)
            .padding(8.dp)
            .clickable { onClick() },

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Box {

            Column {

                AsyncImage(

                    model = anime.images.jpg.image_url,

                    contentDescription = anime.title,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),

                    contentScale = ContentScale.Crop
                )

                Column(

                    modifier = Modifier.padding(12.dp)
                ) {

                    Text(
                        text = anime.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Row(

                        horizontalArrangement = Arrangement.SpaceBetween,

                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(
                            text = "⭐ ${anime.score}"
                        )

                        Text(
                            text = "❤️ ${anime.favorites}"
                        )
                    }
                }
            }

            // =========================
            // BOTON 3 PUNTITOS
            // =========================

            Box(

                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {

                IconButton(

                    onClick = {

                        expanded = true
                    }
                ) {

                    Icon(

                        imageVector = Icons.Default.MoreVert,

                        contentDescription = "Menu"
                    )
                }

                DropdownMenu(

                    expanded = expanded,

                    onDismissRequest = {

                        expanded = false
                    }
                ) {

                    DropdownMenuItem(

                        text = {

                            Text("In Progress")
                        },

                        onClick = {

                            expanded = false

                            viewModel.saveAnimeStatus(
                                anime,
                                "in_progress"
                            )
                        }
                    )

                    DropdownMenuItem(

                        text = {

                            Text("Completed")
                        },

                        onClick = {

                            expanded = false

                            viewModel.saveAnimeStatus(
                                anime,
                                "completed"
                            )
                        }
                    )

                    DropdownMenuItem(

                        text = {

                            Text("Planned")
                        },

                        onClick = {

                            expanded = false

                            viewModel.saveAnimeStatus(
                                anime,
                                "planned"
                            )
                        }
                    )
                }
            }
        }
    }
}