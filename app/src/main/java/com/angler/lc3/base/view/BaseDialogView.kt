package com.angler.lc3.base.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import com.angler.lc3.utils.MGProgressDialog


abstract class BaseDialogView : DialogFragment(), DialogMVPView {

    private var parentActivity: BaseActivity? = null
    //private var progressDialog: ProgressDialog? = null

    var mgProgressDialog: MGProgressDialog? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun show(fragmentManager: FragmentManager, tag: String) {
        val transaction = fragmentManager.beginTransaction()

        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }


    override fun showProgress() {
        hideProgress()
        mgProgressDialog = MGProgressDialog(getBaseActivity())
        mgProgressDialog!!.show()
    }

    override fun hideProgress() {
        if (mgProgressDialog != null && mgProgressDialog?.isShowing!!) {
            mgProgressDialog?.cancel()
        }
    }

    fun dismissDialog(tag: String) {
        dismiss()
        getBaseActivity()?.onFragmentDetached(tag)
    }

    private fun getBaseActivity(): BaseActivity? {
        return parentActivity
    }
}