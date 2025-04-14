package com.natiqhaciyef.frequencecontrollerapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours.Wavetable
import com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours.WavetableInterface
import kotlinx.coroutines.launch
import kotlin.math.exp
import kotlin.math.ln


class WavetableViewModel : ViewModel() {
    private var wavetable = Wavetable.SINE
    private val freqRange = 40f..3000f
    val volumeRange = (-60f)..0f
    private val _volume = MutableLiveData(-24f)
    val volume: LiveData<Float>
        get() = _volume
    private val _frequency = MutableLiveData(300f)
    val frequency: LiveData<Float>
        get() = _frequency

    var stateWavetable: WavetableInterface? = null
        set(value) {
            field = value
            applyParams()
        }

    fun setFrequencySliderValue(freq: Float) {
        viewModelScope.launch {
            val frequencyHzConvert = frequencyHzConverter(freq)
            _frequency.value = frequencyHzConvert
            stateWavetable?.setFrequency(frequencyHzConvert)
        }
    }

    fun setVolumePosition(vol: Float) {
        viewModelScope.launch {
            _volume.value = vol
            stateWavetable?.setVolume(vol)
        }
    }

    fun setWavetableProperty(w: Wavetable) {
        viewModelScope.launch {
            wavetable = w
            stateWavetable?.setWavetable(wavetable)
        }
    }


    fun playClicked() {
        viewModelScope.launch {
            if (stateWavetable?.isPlaying() == true) {
                stateWavetable?.stop()
            } else {
                stateWavetable?.play()
            }
        }
    }

    fun applyParams() {
        viewModelScope.launch {
            stateWavetable?.setFrequency(frequency.value!!)
            stateWavetable?.setVolume(volume.value!!)
            stateWavetable?.setWavetable(wavetable)
        }
    }

    private fun frequencyHzConverter(freq: Float): Float {
        val rangePosition = linearToExponential(freq)
        return valueFromRangePosition(freqRange, rangePosition)
    }

    fun sliderPositionFromFrequencyInHz(freqInHz: Float): Float {
        val rangePosition = rangePositionFromValue(freqRange, freqInHz)
        return exponentialToLinear(rangePosition)
    }

    companion object LinearToExponentialConverter {
        private const val MINIMUM_VALUE = 0.001f
        private val DEFAULT_RANGE = 0f..1f

        fun linearToExponential(freq: Float): Float {
            println("1 - $freq - $DEFAULT_RANGE")
            assert(freq in DEFAULT_RANGE)

            if (freq < MINIMUM_VALUE) {
                return 0f
            }

            return exp(ln(MINIMUM_VALUE) - ln(MINIMUM_VALUE) * freq)
        }

        fun exponentialToLinear(freq: Float): Float {
            println("2 - $freq - $DEFAULT_RANGE")
            assert(freq in DEFAULT_RANGE)

            if (freq < MINIMUM_VALUE) {
                return freq
            }

            return (ln(freq) - ln(MINIMUM_VALUE)) / (ln(-MINIMUM_VALUE))
        }

        fun valueFromRangePosition(
            freqRange: ClosedFloatingPointRange<Float>,
            position: Float
        ): Float {
            println("3 - $position - $freqRange")

            return freqRange.start + (freqRange.endInclusive - freqRange.start) * position
        }

        fun rangePositionFromValue(
            freqRange: ClosedFloatingPointRange<Float>,
            position: Float
        ): Float {
            println("4 - $position - $freqRange")
            assert(position in freqRange)

            return (position - freqRange.start) / (freqRange.endInclusive - freqRange.start)
        }
    }
}