package com.kv.pribizz.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormater {
    var format_dd_MM_yyyy_hh_mm_a =
        SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault())//01-05-2022 07:59 AM
    var format_MMM_dd_yyyy_hh_mm_a =
        SimpleDateFormat("MMM dd yyyy, hh:mm a", Locale.getDefault())//March 19 2022, 10:45 PM
    var format_hh_mm_a =
        SimpleDateFormat("hh:mm a", Locale.getDefault())//10:45 PM
    var format_MMM_dd_yyy =
        SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())//March 19 2022

    var format_dd_MM_yyyy =
        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())//24-03-2022

    var format_dd_MM_yyyy1 =
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())//24/03/2022

    var format_yyyy_MM_dd =
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())//2022-04-30

    var format_MMMM_yyyy =
        SimpleDateFormat("MMMM yyyy", Locale.getDefault())//2022-04-30

    var format_MM_yyyy =
        SimpleDateFormat("MM-yyyy", Locale.getDefault())//04-2022

    var format_yyyy_MM =
        SimpleDateFormat("yyyy-MM", Locale.getDefault())//2022-04

    fun formatDate(
        old_format: SimpleDateFormat,
        new_format: SimpleDateFormat,
        pre_date: String?
    ): String {
        val date = old_format.parse(pre_date)
        return new_format.format(date)
    }

    fun formatDate(
        mili: Long?,
        newFormat: SimpleDateFormat = format_yyyy_MM_dd
    ): String {
        val date = mili?.let { Date(it) }
        return newFormat.format(date)
    }


}