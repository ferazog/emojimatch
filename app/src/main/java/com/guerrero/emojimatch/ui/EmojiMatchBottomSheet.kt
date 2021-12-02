package com.guerrero.emojimatch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme

@Composable
fun EmojiMatchBottomSheetComponent(
    successRate: Float,
    onShare: (score: String) -> Unit,
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
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Success Rate: ${formatSuccessRate(successRate)}%",
            style = MaterialTheme.typography.body2
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { onShare(formatSuccessRate(successRate)) },
        ) {
            Icon(Icons.Filled.Share, contentDescription = "Share")
            Text(
                text = "Share",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Spacer(Modifier.height(8.dp))
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
            EmojiMatchBottomSheetComponent(99.123f, {}, {})
        }
    }
}
