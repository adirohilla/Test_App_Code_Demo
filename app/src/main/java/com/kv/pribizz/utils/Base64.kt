package com.kv.pribizz.utils

import android.util.Base64
import java.io.UnsupportedEncodingException

object Base64 {

    fun getEncodeString(text: String): String? {
        var data = ByteArray(0)
        try {
            data = text.toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return Base64.encodeToString(data, Base64.NO_WRAP)
    }


    fun getDecodeString(base64: String?): String? {
        val data = Base64.decode(base64, Base64.DEFAULT)
        var text: String? = null
        try {

            text = String(data, charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return text
    }
}