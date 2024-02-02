package com.guerrero.emojimatch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guerrero.emojimatch.model.LEVELS
import com.guerrero.emojimatch.model.Level
import com.guerrero.emojimatch.ui.theme.Orange


@Composable
fun MainMenuScreen(
    levels: List<Level>,
    onLevelClicked: (levelId: Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MainTitle()
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(levels) { level ->
                MainMenuItem(
                    level = level,
                    onLevelClick = { onLevelClicked(level.id) }
                )
            }
        }
    }
}

@Composable
fun MainTitle() {
    Text(
        text = "Emoji Match",
        fontSize = 32.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 64.dp, bottom = 16.dp)
    )
}

@Composable
fun MainMenuItem(
    level: Level,
    onLevelClick: () -> Unit
) {
    val cardColor = if (level.locked) {
        Color.LightGray
    } else {
        Orange.copy(alpha = 0.5f)
    }
    Card(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable {
                onLevelClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = level.title,
                modifier = Modifier.weight(1f)
            )
            if (level.locked) {
                Icon(
                    Icons.Filled.Lock,
                    contentDescription = "Locked"
                )
            }
        }
    }
}

@Composable
@Preview
fun MainMenuScreenPreview() {
    MainMenuScreen(LEVELS, {})
}
