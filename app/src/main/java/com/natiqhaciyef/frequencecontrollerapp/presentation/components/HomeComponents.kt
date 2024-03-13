package com.natiqhaciyef.frequencecontrollerapp.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeMute
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.natiqhaciyef.frequencecontrollerapp.R
import com.natiqhaciyef.frequencecontrollerapp.presentation.ui.theme.IndigoColor


@Composable
fun ControllingPanel(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PitchControl(modifier = modifier)
            PlayControl(modifier = modifier)
        }

        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VolumeControl(modifier = modifier)
        }
    }
}

@Composable
fun PitchControl(
    modifier: Modifier
) {
//    var frequency by remember { mutableFloatStateOf(0.0f) }
    var frequency by rememberSaveable { mutableFloatStateOf(0.0f) }
    Text(
        modifier = modifier
            .padding(top = 20.dp),
        text = stringResource(id = R.string.frequency),
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )

    Slider(
        modifier = modifier
            .padding(horizontal = 10.dp),
        value = frequency,
        onValueChange = { frequency = it },
        valueRange = 40f..3000f
    )

    Text(
        modifier = modifier,
        text = stringResource(id = R.string.frequency_value, frequency),
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun PlayControl(
    modifier: Modifier
) {
    val context = LocalContext.current
    var playLabel by rememberSaveable { mutableStateOf("Play") }

    Button(
        modifier = modifier
            .padding(top = 20.dp)
            .width(150.dp)
            .height(45.dp),
        onClick = {
            playLabel = if (playLabel == "Play")
                context.getString(R.string.stop)
            else
                context.getString(R.string.play)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = IndigoColor
        )
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = playLabel,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
            )
        }

    }
}

@Composable
fun VolumeControl(
    modifier: Modifier
) {
    val sliderHeight = LocalConfiguration.current.screenHeightDp / 4
    var volume by rememberSaveable { mutableFloatStateOf(0.0f) }

    Icon(
        imageVector = Icons.Filled.VolumeUp,
        contentDescription = null
    )

    Slider(
        modifier = modifier
            .width(sliderHeight.dp)
            .rotate(270f),
        value = volume,
        onValueChange = {
            volume = it
        },
        valueRange = 0f..100f,
    )

    Icon(
        imageVector = Icons.Filled.VolumeMute,
        contentDescription = null
    )
}


@Composable
fun WavetableSelectionPanel(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.45f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = modifier,
                text = stringResource(id = R.string.wavetable),
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            WavetableSelectionButtons(modifier = modifier)
        }
    }
}

@Composable
fun WavetableSelectionButtons(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (label in arrayOf("Sine", "Triangle", "Square", "Saw")) {
            WavetableButton(
                label = label,
                modifier = modifier,
                onClickAction = {}
            )
        }
    }
}

@Composable
fun WavetableButton(
    label: String,
    modifier: Modifier,
    onClickAction: () -> Unit = {}
) {

    Button(
        modifier = modifier
            .width(150.dp)
            .height(45.dp),
        onClick = onClickAction,
        colors = ButtonDefaults.buttonColors(
            containerColor = IndigoColor
        )
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = label,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White,
            )
        }

    }
}
