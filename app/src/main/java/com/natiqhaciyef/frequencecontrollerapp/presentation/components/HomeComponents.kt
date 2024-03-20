package com.natiqhaciyef.frequencecontrollerapp.presentation.components

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
import androidx.compose.runtime.livedata.observeAsState
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
import com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours.Wavetable
import com.natiqhaciyef.frequencecontrollerapp.presentation.ui.theme.IndigoColor
import com.natiqhaciyef.frequencecontrollerapp.presentation.viewmodel.WavetableViewModel


@Composable
fun ControllingPanel(
    modifier: Modifier,
    viewModel: WavetableViewModel
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
            PitchControl(modifier = modifier, viewModel = viewModel)
            PlayControl(modifier = modifier, viewModel = viewModel)
        }

        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            VolumeControl(modifier = modifier, viewModel = viewModel)
        }
    }
}

@Composable
fun PitchControl(
    modifier: Modifier,
    viewModel: WavetableViewModel
) {
    val frequency = viewModel.frequency.observeAsState()
    val sliderPosition = rememberSaveable {
        mutableStateOf(
            viewModel.sliderPositionFromFrequencyInHz(frequency.value!!)
        )
    }

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
        value = if (!sliderPosition.value.isNaN()) sliderPosition.value else frequency.value!!,
        onValueChange = {
            sliderPosition.value = it
            viewModel.setFrequencySliderValue(it)
        },
        valueRange = 0f..1f
    )

    Text(
        modifier = modifier,
        text =
        stringResource(id = R.string.frequency_value, frequency.value!!),
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
fun PlayControl(
    modifier: Modifier,
    viewModel: WavetableViewModel
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

            viewModel.playClicked()
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
    modifier: Modifier,
    viewModel: WavetableViewModel
) {
    val sliderHeight = LocalConfiguration.current.screenHeightDp / 4
    val volume = viewModel.volume.observeAsState()

    Icon(
        imageVector = Icons.Filled.VolumeUp,
        contentDescription = null
    )

    Slider(
        modifier = modifier
            .width(sliderHeight.dp)
            .rotate(270f),
        value = volume.value!!,
        onValueChange = {
            if (!it.isNaN()) {
                viewModel.setVolumePosition(it)
            }
        },
        valueRange = viewModel.volumeRange,
    )

    Icon(
        imageVector = Icons.Filled.VolumeMute,
        contentDescription = null
    )
}


@Composable
fun WavetableSelectionPanel(
    modifier: Modifier,
    viewModel: WavetableViewModel
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
            WavetableSelectionButtons(modifier = modifier, viewModel = viewModel)
        }
    }
}

@Composable
fun WavetableSelectionButtons(
    modifier: Modifier,
    viewModel: WavetableViewModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (label in Wavetable.entries) {
            WavetableButton(
                label = stringResource(label.returnStringResource()),
                modifier = modifier,
                onClickAction = {
                    viewModel.setWavetableProperty(label)
                }
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