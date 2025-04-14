package com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours

import androidx.annotation.StringRes
import com.natiqhaciyef.frequencecontrollerapp.R

interface WavetableInterface {

    suspend fun play()
    suspend fun stop()
    suspend fun setFrequency(freq: Float)
    suspend fun isPlaying(): Boolean
    suspend fun setVolume(vol: Float)
    suspend fun setWavetable(wavetable: Wavetable)
}

enum class Wavetable{
    SINE{
        override fun returnStringResource(): Int {
            return R.string.sine
        }
    },
    TRIANGLE {
        override fun returnStringResource(): Int {
            return R.string.triangle
        }
    },
    SQUARE {
        override fun returnStringResource(): Int {
            return R.string.square
        }
    },
    SAW {
        override fun returnStringResource(): Int {
            return R.string.saw
        }
    };

    @StringRes
    abstract fun returnStringResource(): Int
}