package com.tracking.helper


import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.angler.lc3.R

class PermissionChecker {

    private val REQUEST_CODE_PERMISSION = 2
    private var mPermissions: ArrayList<String>? = null
    private lateinit var myContext: AppCompatActivity

    // private lateinit var builder: AlertDialog.Builder

    private var alert: AlertDialog? = null

    fun askAllPermissions(context: Context, aPermissions: Array<String>) {
        myContext = context as AppCompatActivity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(context as Activity, aPermissions, REQUEST_CODE_PERMISSION)
        }
    }

    fun checkAllPermission(context: Context, aPermissions: Array<String>): Boolean {
        var isGranted = false
        try {
            val activity = context as Activity
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mPermissions = ArrayList()
                for (i in aPermissions.indices) {
                    if (ContextCompat.checkSelfPermission(
                            activity,
                            aPermissions[i]
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        mPermissions!!.add(aPermissions[i])
                    }
                }

                isGranted = mPermissions!!.isEmpty()

                if (!isGranted) {
                    for (i in mPermissions!!.indices) {
                        askPermission(context, mPermissions!![i])
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return isGranted
        }

        return isGranted
    }

    fun askPermission(context: Context, aPermission: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, aPermission) == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, aPermission)) {
                    mPermissions!!.add(aPermission)
                    ActivityCompat.requestPermissions(
                        context,
                        mPermissions!!.toArray(arrayOfNulls<String>(mPermissions!!.size)),
                        REQUEST_CODE_PERMISSION
                    )
                } else {
                    // TODO Show your force permission alert here
                    Log.d(aPermission, " -----> Never ask again")
                    permissionDialog(context, "Permission Denied!", "Need to give permission")
                }
            }
        }
    }

    fun checkPermission(context: Context, aPermission: String): Boolean {
        var isGranted = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            isGranted = ContextCompat.checkSelfPermission(context, aPermission) == PackageManager.PERMISSION_GRANTED
        }
        return isGranted
    }


    fun permissionDialog(aContext: Activity, aString: String, aString1: String) {
        try {
            val builder = AlertDialog.Builder(aContext)
            builder.setMessage(aString1)
                .setTitle(aString)
                .setCancelable(false)
                .setNegativeButton("Ok") { dialog, id ->
                    dialog.dismiss()
                    alert = null
                }
                .setPositiveButton("GOTO Settings", DialogInterface.OnClickListener { dialog, id ->
                    dialog.dismiss()
                    alert = null
                    callSettings(myContext)
                })

            if (alert == null) {
                alert = builder.create()
                alert?.show()
            }
            // Change the buttons color in dialog
            val pbutton = alert?.getButton(DialogInterface.BUTTON_POSITIVE)
            val nbutton = alert?.getButton(DialogInterface.BUTTON_NEGATIVE)
            pbutton?.setTextColor(ContextCompat.getColor(aContext, R.color.colorPrimary))
            nbutton?.setTextColor(ContextCompat.getColor(aContext, R.color.colorPrimary))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun callSettings(myContext: AppCompatActivity) {
        myContext.startActivity(
            Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", myContext.packageName, null)
            )
        )
    }
}
