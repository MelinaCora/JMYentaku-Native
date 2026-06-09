package com.jmyentaku.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jmyentaku.app.ui.components.GeneralComponent.DrawerContent
import com.jmyentaku.app.ui.components.GeneralComponent.MainTopBar
import com.jmyentaku.app.ui.components.profile.*
import com.jmyentaku.app.ui.navigation.Routes
import com.jmyentaku.app.ui.navigation.passIdAndType
import com.jmyentaku.app.viewmodel.profile.ProfileViewModel
import kotlinx.coroutines.launch
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun ProfileScreen(
    navController: NavController
) {

    val viewModel: ProfileViewModel = viewModel()
    val context = LocalContext.current

    val uiState = viewModel.uiState

    val drawerState =
        rememberDrawerState(
            initialValue = DrawerValue.Closed
        )

    val scope =
        rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onLogout = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Login.route
                    ) {
                        popUpTo(0)
                    }
                },

                onProfileClick = {
                    scope.launch {
                        drawerState.close()
                    }
                },

                onContactClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(Routes.Contact.route)
                },

                onBookmarksClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Bookmarks.route
                    )
                },

                onHomeClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.Home.route
                    )
                },
                onAddedMangaClick = {
                    scope.launch {
                        drawerState.close()
                    }

                    navController.navigate(
                        Routes.AddedManga.route
                    )
                },
                onScanMangaClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(
                        Routes.ScanManga.route
                    )
                }
            )
        }
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Scaffold(
                containerColor = Color.Transparent
            ) { paddingValues ->

                Column(
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
                        .padding(paddingValues)
                        .padding(top = 64.dp)
                ) {

                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        // HEADER
                        item {

                            ProfileHeader(
                                username = uiState.username,
                                title = uiState.title,
                                level = uiState.level,
                                currentXp = uiState.currentXp,
                                xpForNextLevel = uiState.xpForNextLevel,
                                streak = uiState.currentStreak
                            )
                        }

                        // STATS
                        item {

                            Text(
                                text = "Your Stats",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {

                                    ProfileStatCard(
                                        title = "Watching",
                                        value = uiState.watchingCount.toString(),
                                        emoji = "🎬",
                                        modifier = Modifier.weight(1f)
                                    )

                                    ProfileStatCard(
                                        title = "Completed",
                                        value = uiState.completedCount.toString(),
                                        emoji = "✅",
                                        modifier = Modifier.weight(1f)
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {

                                    ProfileStatCard(
                                        title = "Planned",
                                        value = uiState.plannedCount.toString(),
                                        emoji = "📅",
                                        modifier = Modifier.weight(1f)
                                    )

                                    ProfileStatCard(
                                        title = "Hours",
                                        value = uiState.totalAnime.toString(),
                                        emoji = "⏳",
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }

                        // UBICACIÓN - APARTADO
                        item {
                            Text(
                                text = "My Location",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(top = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        item {
                            AndroidView(
                                factory = { ctx ->
                                    Configuration.getInstance().load(
                                        ctx,
                                        androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx)
                                    )

                                    MapView(ctx).apply {
                                        setTileSource(TileSourceFactory.MAPNIK)
                                        setMultiTouchControls(true)

                                        if (ContextCompat.checkSelfPermission(
                                                ctx,
                                                Manifest.permission.ACCESS_FINE_LOCATION
                                            ) == PackageManager.PERMISSION_GRANTED
                                        ) {
                                            try {
                                                val locationManager = ctx.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                                                val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                                                    ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                                                if (lastLocation != null) {
                                                    val userLocation = GeoPoint(lastLocation.latitude, lastLocation.longitude)
                                                    controller.setZoom(15.0)
                                                    controller.setCenter(userLocation)

                                                    val marker = Marker(this)
                                                    marker.position = userLocation
                                                    marker.title = "You are here"
                                                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                                                    overlays.add(marker)
                                                } else {
                                                    controller.setZoom(10.0)
                                                    controller.setCenter(GeoPoint(-34.6037, -58.3816))
                                                }
                                            } catch (e: Exception) {
                                                controller.setZoom(10.0)
                                                controller.setCenter(GeoPoint(-34.6037, -58.3816))
                                            }
                                        } else {
                                            controller.setZoom(5.0)
                                            controller.setCenter(GeoPoint(-34.6037, -58.3816))
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                        }

                        // CHALLENGES
                        item {

                            Text(
                                text = "Daily Challenges",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        items(uiState.challenges) { challenge ->
                            ChallengeProgressCard(
                                challenge = challenge
                            )
                        }

                        // ACHIEVEMENTS
                        item {

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Achievements",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(14.dp))
                        }

                        items(uiState.achievements) { achievement ->
                            AchievementCard(
                                achievement = achievement
                            )
                        }
                    }
                }
            }

            MainTopBar(
                title = "Profile",
                onMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                },
                onSearchResultClick = { id, type ->
                    navController.navigate(passIdAndType(id, type))
                }
            )
        }
    }
}