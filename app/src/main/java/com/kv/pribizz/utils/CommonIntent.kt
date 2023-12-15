package com.kv.pribizz.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.TextUtils

class CommonIntent {
    companion object {

        fun email(sendEmailTo: String, context: Activity) {
            val uri = Uri.parse("mailto:" + sendEmailTo);

            context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }

        fun send(link: String?, context: Activity) {
            val sendIntent = Intent(Intent.ACTION_VIEW)
            sendIntent.data = Uri.parse("sms:")
            sendIntent.putExtra("sms_body", link)
            context.startActivity(sendIntent)
        }

        fun shareOnWhatsapp(context: Activity, text: String?) {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, text)
            try {
                context.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
                whatsappIntent.setPackage("com.whatsapp.w4b")
                try {
                    context.startActivity(whatsappIntent)
                } catch (ex1: ActivityNotFoundException) {
                    Utils.showToast1(context, "App not installed")

                    openonStore("com.whatsapp", context)
                }
            }
        }

        fun shareOnWhatsapp(
            context: Context,
            message: String,
            numberWithCountryCode: String?,
        ) {
            var mobile_number = numberWithCountryCode
            if (!TextUtils.isEmpty(mobile_number) && mobile_number?.startsWith("91") == false) {
                mobile_number = "91" + mobile_number
            }
            val uri =
                Uri.parse("https://api.whatsapp.com/send?phone=$mobile_number&text=$message")
            val sendIntent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(sendIntent)
        }

        fun openonStore(appPackageName: String, context: Activity) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }

        fun openBrowser(url: String, context: Activity) {
            try {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url)
                    )
                )
            } catch (anfe: ActivityNotFoundException) {

            }
        }

        fun shareonTeelegram(text: String?, context: Activity) {
            val shareonInstagram = Intent(Intent.ACTION_SEND)
            shareonInstagram.type = "text/plain"
            shareonInstagram.setPackage("org.telegram.messenger")
            shareonInstagram.putExtra(Intent.EXTRA_TEXT, text)
            try {
                context.startActivity(shareonInstagram)
            } catch (ex: ActivityNotFoundException) {
                Utils.showToast1(context, "App not installed")

                openonStore("org.telegram.messenger", context)
            }
        }
    }
}