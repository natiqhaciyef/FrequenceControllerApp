package com.natiqhaciyef.frequencecontrollerapp.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours.LoggingWavetable
import com.natiqhaciyef.frequencecontrollerapp.presentation.components.ControllingPanel
import com.natiqhaciyef.frequencecontrollerapp.presentation.components.WavetableSelectionPanel
import com.natiqhaciyef.frequencecontrollerapp.presentation.ui.theme.FrequenceControllerAppTheme
import com.natiqhaciyef.frequencecontrollerapp.presentation.viewmodel.WavetableViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: WavetableViewModel by viewModels()
    private var logger = LoggingWavetable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel = ViewModelProvider(this)[WavetableViewModel::class.java]
        viewModel.stateWavetable = logger

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setContent {
            AppTheme { modifier ->
                WavetableSelectionPanel(modifier = modifier, viewModel = viewModel)
                ControllingPanel(modifier = modifier, viewModel = viewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.applyParams()
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

