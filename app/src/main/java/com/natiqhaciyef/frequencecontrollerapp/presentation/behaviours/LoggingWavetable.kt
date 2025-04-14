package com.natiqhaciyef.frequencecontrollerapp.presentation.behaviours

import android.util.Log

class LoggingWavetable : WavetableInterface {
    companion object{
        private const val isPlaying = false
        const val SYNTH_WAVETABLE_APP_TAG = "SYNTH_WAVETABLE_APP"
    }
    override suspend fun play() {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "play() called")
    }

    override suspend fun stop() {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "stop() called")
    }

    override suspend fun setFrequency(freq: Float) {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "setFrequency(freq: Float) called")
    }

    override suspend fun isPlaying(): Boolean {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "isPlaying() called")
        return isPlaying
    }

    override suspend fun setVolume(vol: Float) {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "setVolume(vol: Float) called")
    }

    override suspend fun setWavetable(wavetable: Wavetable) {
        Log.e(SYNTH_WAVETABLE_APP_TAG, "setWavetable(wavetable: Wavetable) called")
    }
}