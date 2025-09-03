package com.example.greetingcard.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp), // A bit edgy for small components
    medium = RoundedCornerShape(16.dp), // Nicely rounded for medium components like Cards
    large = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp) // Expressive top rounding for large sheets/dialogs
)
