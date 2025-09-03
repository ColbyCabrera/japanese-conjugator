package com.example.greetingcard

// General Android/Activity imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

// Core Compose imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign

// Compose Layout imports
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

// Compose Material 3 components
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

// Animation imports
import androidx.compose.animation.AnimatedVisibility

// Icon imports
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit

// App-specific imports
import com.example.greetingcard.data.ConjugationData
import com.example.greetingcard.data.VerbData
import com.example.greetingcard.ui.theme.GreetingCardTheme

// Enum to define the screens in the app
enum class Screen {
    VIEW,
    PRACTICE
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                // Main state to track the current screen
                var currentScreen by remember { mutableStateOf(Screen.VIEW) }
                AppContent(
                    currentScreen = currentScreen,
                    onScreenChange = { currentScreen = it }
                )
            }
        }
    }
}

/**
 * The main content of the app, which includes the Scaffold, FAB navigation,
 * and the content of the current screen.
 */
@Composable
fun AppContent(currentScreen: Screen, onScreenChange: (Screen) -> Unit, modifier: Modifier = Modifier) {
    var isFabExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            // Expandable FAB for navigation
            Column(horizontalAlignment = Alignment.End) {
                AnimatedVisibility(visible = isFabExpanded) {
                    Column {
                        // FAB for Practice Screen
                        FloatingActionButton(
                            onClick = {
                                onScreenChange(Screen.PRACTICE)
                                isFabExpanded = false
                            },
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Practice")
                        }
                        // FAB for View Screen
                        FloatingActionButton(
                            onClick = {
                                onScreenChange(Screen.VIEW)
                                isFabExpanded = false
                            },
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
                            Icon(Icons.Default.Check, contentDescription = "View")
                        }
                    }
                }
                // Main FAB to toggle the menu
                FloatingActionButton(
                    onClick = { isFabExpanded = !isFabExpanded }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Toggle FAB")
                }
            }
        }
    ) { innerPadding ->
        // Content of the screen based on the current state
        when (currentScreen) {
            Screen.VIEW -> JapaneseConjugationScreen(modifier = Modifier.padding(innerPadding))
            Screen.PRACTICE -> PracticeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

/**
 * The screen for practicing verb conjugations.
 */
@Composable
fun PracticeScreen(modifier: Modifier = Modifier) {
    // State for the current question and user's answer
    var currentVerb by remember { mutableStateOf(VerbData.verbs.random()) }
    var currentConjugation by remember { mutableStateOf(ConjugationData.options.random()) }
    var userAnswer by remember { mutableStateOf("") }
    var feedback by remember { mutableStateOf<String?>(null) }

    // Function to get a new question
    fun nextQuestion() {
        currentVerb = VerbData.verbs.random()
        currentConjugation = ConjugationData.options.random()
        userAnswer = ""
        feedback = null
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Practice Mode",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Conjugate '${currentVerb}' to the ${currentConjugation}",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(
                    value = userAnswer,
                    onValueChange = { userAnswer = it },
                    label = { Text("Your answer") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )

                // "Check" button is disabled after checking
                Button(
                    onClick = {
                        // Mock logic for checking the answer
                        feedback = if (Math.random() > 0.5) "Correct!" else "Try again!"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    ),
                    enabled = feedback == null
                ) {
                    Text("Check", style = MaterialTheme.typography.labelLarge)
                }

                // Show feedback and "Next" button after checking
                if (feedback != null) {
                    Text(
                        text = feedback!!,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (feedback == "Correct!") MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.error
                    )
                    Button(
                        onClick = { nextQuestion() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Next", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}

/**
 * The screen for viewing verb conjugations (the "conjugator").
 */
@Composable
fun JapaneseConjugationScreen(modifier: Modifier = Modifier) {
    // State for the verb, result, and dropdown
    var verb by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var selectedConjugationType by remember { mutableStateOf(ConjugationData.options[0]) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "Japanese Verb Conjugator",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                OutlinedTextField(
                    value = verb,
                    onValueChange = { verb = it },
                    label = { Text("Enter verb (e.g., 食べる)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
                    )
                )

                // Dropdown for conjugation type
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = selectedConjugationType,
                        onValueChange = { /* Read-only */ },
                        label = { Text("Conjugation Type") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isDropdownExpanded = true },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                    DropdownMenu(
                        expanded = isDropdownExpanded,
                        onDismissRequest = { isDropdownExpanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ConjugationData.options.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option) },
                                onClick = {
                                    selectedConjugationType = option
                                    isDropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        // Mock logic for conjugation
                        result = if (verb.isNotBlank()) {
                            "Conjugated form of '$verb' to '$selectedConjugationType' (mock)"
                        } else {
                            "Please enter a verb."
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Conjugate", style = MaterialTheme.typography.labelLarge)
                }

                // Show the result in a styled card
                if (result.isNotBlank()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        border = BorderStroke(2.dp, MaterialTheme.colorScheme.tertiary)
                    ) {
                        Text(
                            text = "Result: $result",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
        }
    }
}

// --- Previews ---

@Preview(showBackground = true, name = "Practice Screen Preview")
@Composable
fun PracticeScreenPreview() {
    GreetingCardTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            PracticeScreen()
        }
    }
}

@Preview(showBackground = true, name = "Conjugation Screen Preview")
@Composable
fun JapaneseConjugationScreenPreview() {
    GreetingCardTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            JapaneseConjugationScreen()
        }
    }
}
