package com.angler.lc3.ui.home

import android.support.v7.app.AppCompatActivity
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.service.RetrofitInstance

class HomePresenter(homeActivity: HomeActivity, aContext: AppCompatActivity) : BasePresenter<HomeContract.View>(),
    HomeContract.Presenter {

    private var myRetrofitInstance: RetrofitInstance? = null
    val mHomeView: HomeContract.View = homeActivity
    val myContext: AppCompatActivity = aContext

    init {
        if (myRetrofitInstance == null) {
            myRetrofitInstance = RetrofitInstance()
        }
    }

    override fun initPresenter() {
        mHomeView.initActivity()
    }
}