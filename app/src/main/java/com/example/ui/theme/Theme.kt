package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CyberColorScheme = darkColorScheme(
    primary = PrimaryCyber,
    secondary = SecondaryCyber,
    tertiary = CyberGreen,
    background = CyberBlack,
    surface = CyberGreyDark,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = CyberWhite,
    onSurface = CyberWhite,
    error = CyberRed,
    outline = CyberGreyLight
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CyberColorScheme,
        typography = Typography,
        content = content
    )
}
