package com.angler.lc3.base.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.angler.lc3.utils.MGProgressDialog
import com.angler.lc3.utils.TIDCNetworkManager

abstract class BaseActivity : AppCompatActivity(), MVPView, BaseFragment.CallBack {

    lateinit var mgProgressDialog: MGProgressDialog
    lateinit var myContext: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mgProgressDialog = MGProgressDialog(this@BaseActivity)
        myContext = this
    }

    override fun showProgress() {
        hideProgress()
        mgProgressDialog.show()
        mgProgressDialog.setMessage("Loading")
        mgProgressDialog.setCancelable(false)
    }

    override fun hideProgress() {
        mgProgressDialog.dismiss()
    }

    override fun checkInternet(): Boolean {
        return TIDCNetworkManager.isInternetOnCheck(myContext)
    }

    override fun showToast(aMessage: String) {
        Toast.makeText(myContext, aMessage, Toast.LENGTH_SHORT).show()
    }

    fun hideKeyboard() {
        try {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}