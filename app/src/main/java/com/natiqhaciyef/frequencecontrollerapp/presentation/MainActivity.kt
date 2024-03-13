package com.natiqhaciyef.frequencecontrollerapp.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.natiqhaciyef.frequencecontrollerapp.presentation.components.ControllingPanel
import com.natiqhaciyef.frequencecontrollerapp.presentation.components.WavetableSelectionPanel
import com.natiqhaciyef.frequencecontrollerapp.presentation.ui.theme.FrequenceControllerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            AppTheme { modifier ->
                WavetableSelectionPanel(modifier = modifier)
                ControllingPanel(modifier = modifier)
            }
        }
    }
}

@Composable
fun AppTheme(theme: @Composable (Modifier) -> Unit) {
    FrequenceControllerAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            theme(Modifier)
        }
    }
}

