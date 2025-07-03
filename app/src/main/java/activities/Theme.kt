package com.example.hotel.activities

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.hotel.ui.theme.Typography

class Theme {
}
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE) // This will be MaterialTheme.colors.primary by default in light theme
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    background = Black,
    surface = Color(0xFF121212),
    onPrimary = Black,
    onSecondary = Black,
    onBackground = White,
    onSurface = White,
    error = Color(0xFFCF6679)

)
private val LightColorPalette= lightColorScheme(
    primary = Purple500, // This is your main "purple" color for buttons/app bar
    secondary = Teal200,
    background = White,
    surface = White,
    onPrimary = White, // Text color on primary background
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black,
    error = Color(0xFFB00020)
)

@Composable
fun HotelAppTheme(
    darkTheme:Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val colors= if(darkTheme){
        DarkColorPalette
    }else{
        LightColorPalette
    }
    MaterialTheme  (

        typography = Typography,
        content= content

    )
}
