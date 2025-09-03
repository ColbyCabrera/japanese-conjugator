package com.example.greetingcard.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    small = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
    medium = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
    large = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 24.dp,
        bottomStart = 24.dp,
        bottomEnd = 0.dp
    )
)
