package com.example.greetingcard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.greetingcard.ui.theme.GreetingCardTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JapaneseConjugationScreen(
    modifier: Modifier = Modifier,
    viewModel: VerbViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Japanese Verb Conjugator",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = viewModel.verb,
            onValueChange = { viewModel.onVerbChange(it) },
            label = { Text("Enter verb (e.g., 食べる)") },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
        )

        // Conjugation Type Dropdown
        ExposedDropdownMenuBox(
            expanded = viewModel.isDropdownExpanded,
            onExpandedChange = { viewModel.onDropdownExpand() }
        ) {
            TextField(
                value = viewModel.selectedConjugationType,
                onValueChange = {},
                label = { Text("Conjugation Type") },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = viewModel.isDropdownExpanded)
                },
                modifier = Modifier.fillMaxWidth().menuAnchor(),
                shape = MaterialTheme.shapes.medium,
            )
            ExposedDropdownMenu(
                expanded = viewModel.isDropdownExpanded,
                onDismissRequest = { viewModel.onDropdownDismiss() },
                modifier = Modifier.fillMaxWidth()
            ) {
                viewModel.conjugationOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = { viewModel.onConjugationTypeChange(option) }
                    )
                }
            }
        }

        Button(
            onClick = { viewModel.conjugate() },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Text("Conjugate", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.result.isNotBlank()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(
                    text = viewModel.result,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(24.dp).fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Center
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
