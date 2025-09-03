package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import com.example.greetingcard.ui.theme.GreetingCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JapaneseConjugationScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun JapaneseConjugationScreen(modifier: Modifier = Modifier) {
    var verb by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    val conjugationOptions = listOf("Plain Form (Present)", "Masu Form (Present)", "Te Form", "Nai Form (Negative)")
    var selectedConjugationType by remember { mutableStateOf(conjugationOptions[0]) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Japanese Verb Conjugator",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary // Expressive color
        )

        OutlinedTextField(
            value = verb,
            onValueChange = { verb = it },
            label = { Text("Enter verb (e.g., 食べる)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Conjugation Type Dropdown
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedConjugationType,
                onValueChange = { /* Read-only */ },
                label = { Text("Conjugation Type") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isDropdownExpanded = true }
            )
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                conjugationOptions.forEach { option ->
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
                result = if (verb.isNotBlank()) {
                    "Conjugated form of '$verb' to '$selectedConjugationType' (mock)"
                } else {
                    "Please enter a verb."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Conjugate")
        }

        if (result.isNotBlank()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text(
                    text = "Result: $result",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.secondary // Expressive color
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Conjugation Screen Preview")
@Composable
fun JapaneseConjugationScreenPreview() {
    GreetingCardTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            JapaneseConjugationScreen(modifier = Modifier.padding(16.dp))
        }
    }
}
