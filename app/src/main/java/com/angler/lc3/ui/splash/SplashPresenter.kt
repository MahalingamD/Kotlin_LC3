package com.angler.lc3.ui.splash

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.angler.lc3.BuildConfig
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.helper.TIHelper
import com.angler.lc3.model.Data
import com.angler.lc3.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashPresenter(splashActivity: SplashActivity, aContext: AppCompatActivity) :
    BasePresenter<SplashContract.View>(), SplashContract.Presenter {


    private var myRetrofitInstance: RetrofitInstance? = null
    var myTiSplashView: SplashContract.View = splashActivity
    var myContext: AppCompatActivity = aContext

    init {

        if (this.myRetrofitInstance == null) {
            myRetrofitInstance = RetrofitInstance()
        }
    }


    override fun initPresenter() {
        getView()?.splashCount();
    }

    override fun checkVersionDetail() {
        try {
            if (getView()!!.checkInternet()) {

                myRetrofitInstance!!.api.getAppUpdate(BuildConfig.VERSION_NAME)
                    .enqueue(object : Callback<Data> {
                        override fun onResponse(call: Call<Data>, response: Response<Data>) {

                            getView()!!.hideProgress()
                            val data = response.body()

                            if (data?.response != null) {
                                val aString = data.response.response_code
                                if (aString == "1") {
                                    val aVersionDetails = data.versionDetails
                                    aVersionDetails?.let { getView()!!.appUpdate(it) }
                                } else {
                                    getView()!!.callNextActivity()
                                }
                            }
                        }

                        override fun onFailure(call: Call<Data>, t: Throwable) {
                            try {
                                getView()!!.hideProgress()
                                TIHelper.showAlertDialog(myContext, "Something went wrong!")
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        }
                    })
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }



}