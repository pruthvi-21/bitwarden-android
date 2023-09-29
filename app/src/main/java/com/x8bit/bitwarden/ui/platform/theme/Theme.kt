package com.x8bit.bitwarden.ui.platform.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.ColorRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.x8bit.bitwarden.R

/**
 * The overall application theme. This can be configured to support a [darkTheme] and
 * [dynamicColor].
 */
@Composable
fun BitwardenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    // Get the current scheme
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme(context)
        else -> lightColorScheme(context)
    }

    // Update status bar according to scheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    // Set overall theme based on color scheme and typography settings
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

private fun darkColorScheme(context: Context): ColorScheme =
    darkColorScheme(
        primary = R.color.dark_primary.toColor(context),
        onPrimary = R.color.dark_on_primary.toColor(context),
        primaryContainer = R.color.dark_primary_container.toColor(context),
        onPrimaryContainer = R.color.dark_on_primary_container.toColor(context),
        secondary = R.color.dark_secondary.toColor(context),
        onSecondary = R.color.dark_on_secondary.toColor(context),
        secondaryContainer = R.color.dark_secondary_container.toColor(context),
        onSecondaryContainer = R.color.dark_on_secondary_container.toColor(context),
        tertiary = R.color.dark_tertiary.toColor(context),
        onTertiary = R.color.dark_on_tertiary.toColor(context),
        tertiaryContainer = R.color.dark_tertiary_container.toColor(context),
        onTertiaryContainer = R.color.dark_on_tertiary_container.toColor(context),
        error = R.color.dark_error.toColor(context),
        onError = R.color.dark_on_error.toColor(context),
        errorContainer = R.color.dark_error_container.toColor(context),
        onErrorContainer = R.color.dark_on_error_container.toColor(context),
        surface = R.color.dark_surface.toColor(context),
        onSurface = R.color.dark_on_surface.toColor(context),
        onSurfaceVariant = R.color.dark_on_surface_variant.toColor(context),
        outline = R.color.dark_outline.toColor(context),
        outlineVariant = R.color.dark_outline_variant.toColor(context),
        inverseSurface = R.color.dark_inverse_surface.toColor(context),
        inverseOnSurface = R.color.dark_inverse_on_surface.toColor(context),
        inversePrimary = R.color.dark_inverse_primary.toColor(context),
        scrim = R.color.dark_scrim.toColor(context),
    )

private fun lightColorScheme(context: Context): ColorScheme =
    lightColorScheme(
        primary = R.color.primary.toColor(context),
        onPrimary = R.color.on_primary.toColor(context),
        primaryContainer = R.color.primary_container.toColor(context),
        onPrimaryContainer = R.color.on_primary_container.toColor(context),
        secondary = R.color.secondary.toColor(context),
        onSecondary = R.color.on_secondary.toColor(context),
        secondaryContainer = R.color.secondary_container.toColor(context),
        onSecondaryContainer = R.color.on_secondary_container.toColor(context),
        tertiary = R.color.tertiary.toColor(context),
        onTertiary = R.color.on_tertiary.toColor(context),
        tertiaryContainer = R.color.tertiary_container.toColor(context),
        onTertiaryContainer = R.color.on_tertiary_container.toColor(context),
        error = R.color.error.toColor(context),
        onError = R.color.on_error.toColor(context),
        errorContainer = R.color.error_container.toColor(context),
        onErrorContainer = R.color.on_error_container.toColor(context),
        surface = R.color.surface.toColor(context),
        onSurface = R.color.on_surface.toColor(context),
        onSurfaceVariant = R.color.on_surface_variant.toColor(context),
        outline = R.color.outline.toColor(context),
        outlineVariant = R.color.outline_variant.toColor(context),
        inverseSurface = R.color.inverse_surface.toColor(context),
        inverseOnSurface = R.color.inverse_on_surface.toColor(context),
        inversePrimary = R.color.inverse_primary.toColor(context),
        scrim = R.color.scrim.toColor(context),
    )

@ColorRes
private fun Int.toColor(context: Context): Color =
    Color(context.getColor(this))
