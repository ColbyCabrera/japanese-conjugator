package com.example.greetingcard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

// Expressive Typography
val Typography = Typography(
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default, // Consider FontFamily.Serif for a more traditional feel
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center // Center align the main title
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.15.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle( // For Buttons
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.5.sp
    )
    /*
    // You can also customize other default text styles:
    displayLarge = TextStyle(...),
    displayMedium = TextStyle(...),
    displaySmall = TextStyle(...),
    headlineLarge = TextStyle(...),
    headlineSmall = TextStyle(...),
    titleLarge = TextStyle(...),
    titleSmall = TextStyle(...),
    bodyMedium = TextStyle(...),
    bodySmall = TextStyle(...),
    labelMedium = TextStyle(...),
    labelSmall = TextStyle(...),
    */
)
