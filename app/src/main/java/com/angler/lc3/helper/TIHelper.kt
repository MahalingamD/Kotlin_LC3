package com.angler.lc3.helper

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.os.Build
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.widget.ImageView
import com.angler.lc3.R
import com.angler.lc3.data.entity.BladeTypes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*

class TIHelper {
    companion object {
        var TAG = TIHelper::class.java.simpleName


        /**
         * @param aMessage aMessage
         */
        public fun showAlertDialog(aContext: AppCompatActivity, aMessage: String) {
            try {
                val builder = AlertDialog.Builder(aContext)
                builder.setMessage(aMessage)
                    .setTitle(aContext.getString(R.string.app_name))
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, id -> dialog.dismiss() }

                val alert = builder.create()
                alert.show()
                // Change the buttons color in dialog
                val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
                pbutton.setTextColor(ContextCompat.getColor(aContext, R.color.black))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

        fun permissionDialog(aContext: Activity, aString: String, aString1: String) {
            try {
                val builder = AlertDialog.Builder(aContext)
                builder.setMessage(aString1)
                    .setTitle(aString)
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, id -> dialog.dismiss() }

                val alert = builder.create()
                alert.show()
                // Change the buttons color in dialog
                val pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
                pbutton.setTextColor(ContextCompat.getColor(aContext, R.color.black))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun getDeviceHeight(aContext: AppCompatActivity): String {
            val displaymetrics = DisplayMetrics()
            aContext.windowManager.defaultDisplay.getMetrics(displaymetrics)
            return displaymetrics.heightPixels.toString()
        }

        fun getDeviceWidth(aContext: AppCompatActivity): String {
            val displaymetrics = DisplayMetrics()
            aContext.windowManager.defaultDisplay.getMetrics(displaymetrics)
            return displaymetrics.widthPixels.toString()
        }


        fun isTabletDevice(aContext: FragmentActivity): String {
            var aTabletSize: Boolean
            try {
                aTabletSize = aContext.resources.getBoolean(R.bool.isTablet)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, e.cause.toString())
                aTabletSize = false
            }

            return if (aTabletSize)
                "Tablet"
            else
                "Mobile"

        }


        fun changeLang(context: Context, lang_code: String): ContextWrapper {
            var context = context
            val sysLocale: Locale

            val rs = context.resources
            val config = rs.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sysLocale = config.locales.get(0)
            } else {
                sysLocale = config.locale
            }

            if (lang_code != "" && sysLocale.language != lang_code) {
                val country = "IN"
                val locale = Locale(lang_code, country)

                //Locale locale = new Locale( lang_code );
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    config.setLocale(locale)
                } else {
                    config.locale = locale
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    context = context.createConfigurationContext(config)
                } else {
                    context.resources.updateConfiguration(config, context.resources.displayMetrics)
                }
            }


            return ContextWrapper(context)
        }



    }
}