package com.kv.pribizz.model.response

import com.kv.pribizz.model.BaseResponseModel

data class MarqueeResponseModel(
    val data: ArrayList<MarqueeModel>


) : BaseResponseModel() {
    fun getMarqueeText(): String {
        var text = ""
        for (x in data) {
            text += "◙ ${x.marquee_text} \t\t"
        }
        return text;
    }
}

data class MarqueeModel(val marquee_text: String)
