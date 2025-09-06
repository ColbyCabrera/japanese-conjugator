package com.example.japaneseconjugationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingToolbarState
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalHorizontalFloatingToolbarOverride
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.japaneseconjugationapp.ui.theme.JapaneseConjugationappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseConjugationappTheme {
                MainAppScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun MainAppScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Main content of your app will go here
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Japanese Conjugation App", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Content Area", style = MaterialTheme.typography.bodyLarge)
                FloatingToolbarState(
                    initialOffset = 0F,
                    initialOffsetLimit = 0F,
                    initialContentOffset = 0F,
                )

            }



            /*  FloatingNavigationBar(
                  modifier = Modifier
                      .align(Alignment.BottomCenter)
                      .fillMaxWidth() // Make the bar a bit wider
                      .padding(
                          bottom = 16.dp,
                          start = 24.dp,
                          end = 24.dp
                      ) // Adjust padding for a more "floating" look
              ) */
        }
    }
}

@Composable
fun FloatingNavigationBar(modifier: Modifier = Modifier) {
    // HorizontalFloatingToolbar()

}

@Composable
fun HorizontalFloatingNavToolbar() {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Animated visibility for the navigation action buttons
        AnimatedVisibility(visible = isExpanded) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Using Extended FABs for navigation with text
                ExtendedFloatingActionButton(
                    onClick = { /* Navigate to Home */ },
                    icon = { Icon(Icons.Default.Home, "Home") },
                    text = { Text("Home") }
                )
                ExtendedFloatingActionButton(
                    onClick = { /* Navigate to Cart */ },
                    icon = { Icon(Icons.Default.ShoppingCart, "Cart") },
                    text = { Text("Cart") }
                )
                ExtendedFloatingActionButton(
                    onClick = { /* Navigate to Settings */ },
                    icon = { Icon(Icons.Default.Settings, "Settings") },
                    text = { Text("Settings") }
                )
            }
        }

        // Main FAB to toggle expansion
        FloatingActionButton(
            onClick = { isExpanded = !isExpanded }
        ) {
            Icon(
                if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Toggle Navigation"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JapaneseConjugationappTheme {
        MainAppScreen()
    }
}
