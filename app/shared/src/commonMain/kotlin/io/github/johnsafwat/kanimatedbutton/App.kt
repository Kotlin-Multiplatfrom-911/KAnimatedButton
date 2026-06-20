package io.github.johnsafwat.kanimatedbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
@Preview
fun App() {
    MaterialTheme {
        // Tapping "Submit" kicks off the loading animation; it resets itself after 2s
        // (the button ignores clicks while loading, so we drive the reset here).
        var isLoading by remember { mutableStateOf(false) }
        LaunchedEffect(isLoading) {
            if (isLoading) {
                delay(2000)
                isLoading = false
            }
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            // Filled, large rectangle — tap to play the collapse-to-spinner animation
            KAnimatedButton(
                buttonType = KAnimatedButtonType.LARGE_RECTANGLE,
                title = "Submit",
                isEnabled = true,
                isLoading = isLoading,
                onClick = { isLoading = true },
            )

            // Filled, fully rounded
            KAnimatedButton(
                buttonType = KAnimatedButtonType.LARGE_ROUNDED,
                title = "Rounded",
                isEnabled = true,
                onClick = {},
            )

            // Outlined, sized to its content
            KAnimatedButton(
                buttonType = KAnimatedButtonType.OUTLINE_LARGE_RECTANGLE,
                title = "Outlined",
                isEnabled = true,
                wrapContentWidth = true,
                onClick = {},
            )

            // Disabled
            KAnimatedButton(
                buttonType = KAnimatedButtonType.MEDIUM_RECTANGLE,
                title = "Disabled",
                isEnabled = false,
                onClick = {},
            )

            // Custom content via the `child` slot
            KAnimatedButton(
                buttonType = KAnimatedButtonType.SMALL_RECTANGLE,
                isEnabled = true,
                wrapContentWidth = true,
                onClick = {},
                child = { Text("Custom child") },
            )
        }
    }
}
