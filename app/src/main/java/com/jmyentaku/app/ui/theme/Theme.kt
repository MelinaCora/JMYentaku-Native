package com.jmyentaku.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

// ------------------------
// 🎨 PALETA CELESTE MODERNA
// ------------------------

// Light
val SkyBlue40 = Color(0xFF0288D1)
val BlueGrey40 = Color(0xFF546E7A)
val Aqua40 = Color(0xFF00ACC1)

// Dark
val SkyBlue80 = Color(0xFF81D4FA)
val BlueGrey80 = Color(0xFFB0BEC5)
val Aqua80 = Color(0xFF80DEEA)

// ------------------------
// 🌙 DARK THEME
// ------------------------
private val DarkColorScheme = darkColorScheme(
    primary = JMY_SkyBlue40,
    secondary = JMY_BlueGrey40,
    tertiary = JMY_Aqua40
)

// ------------------------
// ☀️ LIGHT THEME
// ------------------------
private val LightColorScheme = lightColorScheme(
    primary = JMY_SkyBlue80,
    secondary = JMY_BlueGrey80,
    tertiary = JMY_Aqua80
)

@Composable
fun JMYentakuTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}