package com.example.login_signup.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.login_signup.R

private val CustomFontFamily = FontFamily(
    Font(R.font.font_resource) // Reemplaza con el nombre de tu archivo de fuente
)

private val CustomTypography = Typography(
    // Aplica la fuente personalizada a los diferentes estilos
    displayLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    displayMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displaySmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    titleLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    titleMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleSmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = CustomFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    onError = OnErrorColor
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    error = ErrorColor,
    onPrimary = OnPrimaryColor,
    onSecondary = OnSecondaryColor,
    onBackground = OnBackgroundColor,
    onSurface = OnSurfaceColor,
    onError = OnErrorColor
)

@Composable
fun LoginSignupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography, // Aplica la tipografía personalizada
        content = content
    )
}
