package com.felipeerazog.emojimatch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeerazog.emojimatch.R
import com.felipeerazog.emojimatch.ui.theme.EmojiMatchTheme

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
            style = MaterialTheme.typography.bodyLarge
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
            Icon(
                painterResource(R.drawable.round_share_24),
                contentDescription = "Share"
            )
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
