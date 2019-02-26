package com.angler.lc3.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class TIDCNetworkManager {

    /**
     * Check the internet connection and return true or false
     *
     * @param aContext Context
     * @return boolean
     */

    fun isInternetOn(aContext: Context?): Boolean {
        try {
            try {
                if (aContext == null)
                    return false

                val connectivityManager = aContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                return activeNetworkInfo != null
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    companion object {

        fun isInternetOnCheck(aContext: Context): Boolean {

            var aResult = false

            val aConnecMan = aContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //
            if (aConnecMan.getNetworkInfo(0).state == NetworkInfo.State.CONNECTED
                || aConnecMan.getNetworkInfo(0).state == NetworkInfo.State.CONNECTING
                || aConnecMan.getNetworkInfo(1).state == NetworkInfo.State.CONNECTING
                || aConnecMan.getNetworkInfo(1).state == NetworkInfo.State.CONNECTED
            ) {

                aResult = true

            } else if (aConnecMan.getNetworkInfo(0).state == NetworkInfo.State.DISCONNECTED || aConnecMan.getNetworkInfo(
                    1
                ).state == NetworkInfo.State.DISCONNECTED
            ) {

                aResult = false
            }

            return aResult
        }
    }
}