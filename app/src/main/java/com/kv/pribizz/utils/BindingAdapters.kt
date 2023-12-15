package com.kv.pribizz.utils

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.ParseException
import java.util.*

class BindingAdapters {
    companion object {
        @JvmStatic
        @BindingAdapter("date_from", "date_to", requireAll = true)
        fun TextView.setDate(mili_from: Long, mili_to: Long) {
            try {
                val datefrom = DateFormater.format_dd_MM_yyyy1.format(Date(mili_from))
                val dateto = DateFormater.format_dd_MM_yyyy1.format(Date(mili_to))

                this.text = "$datefrom - $dateto"
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("date")
        fun TextView.setDate(date: String) {
            try {
                val parsed_date = DateFormater.format_yyyy_MM.parse(date)
                val formated_date = DateFormater.format_MMMM_yyyy.format(parsed_date)

                this.text = formated_date
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("date1")
        fun TextView.setDate1(date: String?) {
            try {
                if (!TextUtils.isEmpty(date)) {
                    val parsed_date = DateFormater.format_yyyy_MM_dd.parse(date)
                    val formated_date = DateFormater.format_dd_MM_yyyy.format(parsed_date)

                    this.text = formated_date
                }
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter(
            "srcUrl",
            "circleCrop",
            "placeholder",
            requireAll = false // make the attributes optional
        )
        fun ImageView.bindSrcUrl(
            url: String?,
            circleCrop: Boolean = false,
            placeholder: Drawable?,
        ) = Glide.with(this).load(url).let { request ->

            if (circleCrop) {
                request.circleCrop()
            }

            if (placeholder != null) {
                request.placeholder(placeholder)
                request.error(placeholder)
            }

            request.into(this)
        }
    }
}