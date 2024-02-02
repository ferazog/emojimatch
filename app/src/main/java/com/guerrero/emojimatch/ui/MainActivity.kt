package com.guerrero.emojimatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.guerrero.emojimatch.model.LEVELS

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainMenuScreen(
                levels = LEVELS,
                onLevelClicked = { levelId ->
                    startActivity(
                        Intent(this, GameActivity::class.java)
                            .putExtra(EXTRA_LEVEL_ID, levelId)
                    )
                }
            )
        }
    }


}
