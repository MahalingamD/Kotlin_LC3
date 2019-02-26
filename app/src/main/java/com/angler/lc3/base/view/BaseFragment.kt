package com.angler.lc3.base.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Toast
import com.angler.lc3.utils.MGProgressDialog
import com.angler.lc3.utils.TIDCNetworkManager

abstract class BaseFragment : Fragment(), MVPView {

    lateinit var myContext: FragmentActivity

    private var parentActivity: BaseActivity? = null

    lateinit var mgProgressDialog: MGProgressDialog


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
            myContext = this.activity!!

            mgProgressDialog = MGProgressDialog(myContext)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    override fun showProgress() {
        hideProgress()
        mgProgressDialog.show()
        mgProgressDialog.setMessage("loading")
        mgProgressDialog.setCancelable(false)
    }

    override fun hideProgress() {
        mgProgressDialog.dismiss()
    }

    fun getBaseActivity() = parentActivity


    override fun checkInternet(): Boolean {
        return TIDCNetworkManager.isInternetOnCheck(myContext)
    }

    override fun showToast(aMessage: String) {
        Toast.makeText(myContext, aMessage, Toast.LENGTH_SHORT).show()
    }

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }


    abstract fun setUp()
    abstract fun onResumeFragment()
}