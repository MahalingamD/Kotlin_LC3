package com.angler.lc3.ui.contact

import android.support.v4.app.FragmentActivity
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.data.AppDatabase

class ContactPresenter(
    val contactFragment: ContactFragment,
    val mContext: FragmentActivity
) : BasePresenter<ContactContract.View>(), ContactContract.Presenter {

    lateinit var mAppDatabase: AppDatabase

    init {
        mAppDatabase = AppDatabase.getDatabase(mContext)
    }

    override fun getState() {
        val aStateList = mAppDatabase.stateMasterDao().getAllState("1")

        contactFragment.setState(aStateList)
    }

    override fun getCity(aStateId: String) {
        val aCityList = mAppDatabase.cityMasterDao().getAllCity(aStateId, "1")
        contactFragment.setCity(aCityList)
    }

    override fun getAddress(aStateId: String, aCityId: String) {
        val aAddressList = mAppDatabase.addressMasterDao().getAllAddress(aStateId, aCityId, "1")
        contactFragment.setAddress(aAddressList)
    }
}