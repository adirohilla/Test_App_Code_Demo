package com.kv.pribizz.utils

import android.util.Log
import com.kv.pribizz.BuildConfig

object Logger {
    fun e(message: String) {
        if (BuildConfig.DEBUG) {
            val tag = "Pribizz"
            Log.e(tag, message)
        }
    }
}