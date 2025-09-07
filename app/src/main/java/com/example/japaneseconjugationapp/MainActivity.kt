package com.example.japaneseconjugationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingToolbarState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


    BottomAppBarExample()

}

/**
 * A wrapper composable for the Material 3 FloatingToolbar.
 *
 * This simplifies the call by forwarding the state, modifier, and content
 * directly to the official Material 3 component.
 *
 * @param state The state object to be used to control or observe the FloatingToolbar's state.
 * @param modifier The modifier to be applied to this layout.
 * @param content The content of the toolbar, typically IconButtons.
 */
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FloatingToolbar(
    state: FloatingToolbarState,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    // Calling the official Material 3 component and passing the parameters.
    // The full package name is used to avoid a recursive call to this wrapper function.

}

@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(Icons.Filled.Check, contentDescription = "Localized description")
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Mic,
                        contentDescription = "Localized description",
                    )
                }
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        Icons.Filled.Image,
                        contentDescription = "Localized description",
                    )
                }
            }, floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* do something */ },
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(Icons.Filled.Add, "Localized description")
                }
            })
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Example of a scaffold with a bottom app bar."
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JapaneseConjugationappTheme {
        MainAppScreen()
    }
}
