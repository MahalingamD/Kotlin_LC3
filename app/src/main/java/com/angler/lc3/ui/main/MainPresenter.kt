package com.angler.lc3.ui.main

import android.support.v4.app.FragmentActivity
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.Product
import com.angler.lc3.service.RetrofitInstance
import com.angler.lc3.ui.main.MainContract.Presenter

class MainPresenter(
    mainFragment: MainFragment, aContext: FragmentActivity
) : BasePresenter<MainContract.View>(), Presenter {

    var mRetrofitInstance: RetrofitInstance? = null
    var myContext: FragmentActivity = aContext
    var mMainFragment: MainContract.View = mainFragment
    var mAppDatabase: AppDatabase? = null

    init {
        mAppDatabase = AppDatabase.getDatabase(myContext)

        if (mRetrofitInstance == null) {
            mRetrofitInstance = RetrofitInstance()
        }
    }

    override fun initPresenter() {

    }


    override fun getProduct() {
        val aProductList: List<Product> = mAppDatabase!!.ProductModel().getAllsProducts("2", "1")

        mMainFragment.setProduct(aProductList)
    }


}