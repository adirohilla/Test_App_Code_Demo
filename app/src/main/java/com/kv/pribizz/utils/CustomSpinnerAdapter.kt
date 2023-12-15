package com.kv.pribizz.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class CustomSpinnerAdapter(context: Context, val spinner: Spinner, items: Array<String>) :
    ArrayAdapter<String>(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        items
    ) {
    override fun getDropDownView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val view: TextView = super.getDropDownView(
            position,
            convertView,
            parent
        ) as TextView
        // set item text bold
        view.setTypeface(view.typeface, Typeface.BOLD)

        // set selected item style
        if (position == spinner.selectedItemPosition && position != 0) {
            view.background = ColorDrawable(Color.parseColor("#F7E7CE"))
            view.setTextColor(Color.parseColor("#333399"))
        }

        // make hint item color gray
        if (position == 0) {
            view.setTextColor(Color.LTGRAY)
        }

        return view
    }

    override fun isEnabled(position: Int): Boolean {
        // disable first item
        // first item is display as hint
        return position != 0
    }
}