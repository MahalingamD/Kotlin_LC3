package com.angler.lc3.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.base.view.BaseActivity
import com.pnikosis.materialishprogress.ProgressWheel


class MGProgressDialog : Dialog {

    private var myContext: FragmentActivity? = null
    private var myAppContext: Context? = null
    private var myLoadingTxt: TextView? = null
    private var myProgressWheel: ProgressWheel? = null

    constructor(context: BaseActivity?) : super(context) {
        myContext = context
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.setContentView(R.layout.custom_dialog_box)
            this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myLoadingTxt = this.findViewById(R.id.custom_dialog_box_TXT_loading) as TextView
            myProgressWheel = this.findViewById(R.id.custom_dialog_box_PB_loading) as ProgressWheel
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    constructor(context: FragmentActivity?) : super(context) {
        myAppContext = context
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.setContentView(R.layout.custom_dialog_box)
            this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myLoadingTxt = this.findViewById(R.id.custom_dialog_box_TXT_loading) as TextView
            myProgressWheel = this.findViewById(R.id.custom_dialog_box_PB_loading) as ProgressWheel
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

   /* constructor(context: Activity) : super(context) {
        myAppContext = context
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.setContentView(R.layout.custom_dialog_box)
            this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myLoadingTxt = this.findViewById(R.id.custom_dialog_box_TXT_loading) as TextView
            myProgressWheel = this.findViewById(R.id.custom_dialog_box_PB_loading) as ProgressWheel
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    constructor(applicationContext: Context?) : super(applicationContext) {
        myAppContext = applicationContext
        try {
            this.requestWindowFeature(Window.FEATURE_NO_TITLE)
            this.setContentView(R.layout.custom_dialog_box)
            this.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myLoadingTxt = this.findViewById(R.id.custom_dialog_box_TXT_loading) as TextView
            myProgressWheel = this.findViewById(R.id.custom_dialog_box_PB_loading) as ProgressWheel
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/

    /**
     * Loading text
     *
     * @param aLoadingText
     */
    fun setMessage(aLoadingText: String) {
        myLoadingTxt!!.text = aLoadingText
    }

    /*@Override
    public void show() {
        super.show();
    }*/

}
