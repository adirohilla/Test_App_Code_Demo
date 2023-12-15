package com.kv.pribizz.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kv.pribizz.R
import com.kv.pribizz.utils.Utils.showToast
import java.util.*

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
}

object Utils {

    fun Context.copyToClipboard(txt: String) {
        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "nonsense_data",
            txt
        )
        clipboardManager.setPrimaryClip(clipData)
        showToast("Copied to clipboard")
    }

    fun hasInternetConnection(context: Context?): Boolean {
        if (context == null)
            return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun fullScreen(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //show the activity in full screen

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun showPasswordEditText(context: Context, et_password: EditText) {
        et_password.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.rawX >= (et_password.right - et_password.compoundDrawables[2].bounds.width())
                    ) {
                        //clicked
                        showPassword(context, et_password)
                        return true
                    }
                }
                return false
            }
        }
        )
    }

    fun showPassword(context: Context, et_password: EditText) {
        val password = et_password.text.toString()
        if (et_password.tag != null && et_password.tag.toString()
                .equals("hide", ignoreCase = true)
        ) {
            et_password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            et_password.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_lock_open_24),
                null
            )
            et_password.tag = "show"
        } else {
            et_password.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                ContextCompat.getDrawable(context, R.drawable.ic_baseline_lock_24),
                null
            )
            et_password.tag = "hide"
            et_password.inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        et_password.setText("")
        et_password.append(password)
    }

    fun showToast1(context: Context, text: String, duration: Int = Toast.LENGTH_SHORT) {
        if (!text.isNullOrEmpty()) {
            Toast.makeText(context, text, duration).show()
        }
    }

    fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
        if (!text.isNullOrEmpty()) {
            Toast.makeText(this, text, duration).show()
        }
    }

    fun Fragment.showToast(text: String?, duration: Int = Toast.LENGTH_SHORT) {
        if (!text.isNullOrEmpty()) {
            Toast.makeText(this.requireContext(), text, duration).show()
        }
    }

    fun Fragment.showSnackBar(text: String?) {
        if (!text.isNullOrEmpty()) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                text,
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }


    fun Activity.showSnackBar(text: String?) {
        if (!text.isNullOrEmpty()) {
            Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    fun showSnackBar1(activity: Activity, text: String?) {
        if (!text.isNullOrEmpty()) {
            Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    fun getUniqueDeviceId(): String {
        Log.e("zZZ", Settings.Secure.ANDROID_ID + "<")
        val m_szDevIDShort = ("35"
                + Build.BOARD.length % 10
                + Build.BRAND.length % 10
                + Build.CPU_ABI.length % 10
                + Build.DEVICE.length % 10
                + Build.MANUFACTURER.length % 10
                + Build.MODEL.length % 10
                + Build.PRODUCT.length % 10)
        var serial: String? = null
        try {
            serial = Build::class.java.getField(Build.SERIAL)[null].toString()
            return UUID(
                m_szDevIDShort.hashCode().toLong(),
                serial.hashCode().toLong()
            ).toString()
        } catch (exception: java.lang.Exception) {
            serial = "serial"
        }
        return UUID(m_szDevIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
    }

    fun hideKeyboard1(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Fragment.hideKeyboard() {
        view.let {
            activity?.hideKeyboard()
        }
    }

    fun Context.hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}