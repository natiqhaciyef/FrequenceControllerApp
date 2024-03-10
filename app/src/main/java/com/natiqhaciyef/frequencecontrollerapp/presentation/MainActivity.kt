package com.natiqhaciyef.frequencecontrollerapp.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.natiqhaciyef.frequencecontrollerapp.presentation.ui.theme.FrequenceControllerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        setContent {
            AppTheme {

            }
        }
    }
}

@Composable
fun AppTheme(theme: @Composable () -> Unit) {
    FrequenceControllerAppTheme {
//        Surface(
//            modifier = Modifier.fillMaxSize(),
//            color = MaterialTheme.colorScheme.background
//        ) {
            theme()
//        }
    }
}

