package com.guerrero.emojimatch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme

@Composable
fun EmojiMatchBottomSheetComponent(
    onShare: () -> Unit,
    onPlayAgain: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congratulations! 👏",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Do you like the game?",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { onShare() },
        ) {
            Icon(Icons.Filled.Share, contentDescription = "Share")
            Text(
                text = "Share",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        TextButton(
            onClick = onPlayAgain
        ) {
            Text("Play Again")
        }
    }
}

fun formatSuccessRate(successRate: Float): String {
    return if (successRate == 100f) {
        String.format("%.0f", successRate)
    } else {
        String.format("%.2f", successRate)
    }
}

@Preview(showBackground = true)
@Composable
fun EmojiMatchComponentPreview() {
    EmojiMatchTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            EmojiMatchBottomSheetComponent({}, {})
        }
    }
}
