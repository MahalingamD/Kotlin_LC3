package com.angler.lc3.ui.login

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.angler.lc3.R
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.*
import com.angler.lc3.helper.TIHelper
import com.angler.lc3.model.Data
import com.angler.lc3.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(aloginActivity: LoginActivity, aContext: AppCompatActivity) :
    BasePresenter<LoginContract.View>(), LoginContract.Presenter {


    companion object {
        var TAG = LoginPresenter::class.java.simpleName
    }

    var mAppDatabase: AppDatabase
    var mLoginView: LoginContract.View = aloginActivity
    var myContext: AppCompatActivity = aContext
    private var myRetrofitInstance: RetrofitInstance? = null

    init {

        mAppDatabase = AppDatabase.getDatabase(myContext)

        if (myRetrofitInstance == null) {
            myRetrofitInstance = RetrofitInstance()
        }
    }


    override fun initPresenter() {
        mLoginView.init()
    }

    override fun getLanguage() {
        try {
            if (mLoginView.checkInternet()) {
                mLoginView.showProgress()
                myRetrofitInstance!!.api.allLanguage.enqueue(object : Callback<Data> {
                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                        mLoginView.hideProgress()
                        val data = response.body()

                        if (data?.response != null) {
                            val aString = data.response.response_code

                            if (aString == "1") {
                                val aLaguageList = data.getMasterList
                                mAppDatabase.languageModel().insertAllLanguage(aLaguageList?.languageMaster!!)
                                mLoginView.setLanguageAdapter(aLaguageList.languageMaster!!)

                            } else {
                                TIHelper.showAlertDialog(myContext, data.response.response_message.toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<Data>, t: Throwable) {
                        mLoginView.hideProgress()
                        TIHelper.showAlertDialog(myContext, "Something went wrong!")
                    }
                })
            } else {
                TIHelper.showAlertDialog(
                    myContext,
                    myContext.resources.getString(R.string.alert_message_check_internet)
                )
            }
        } catch (e: Exception) {
            Log.e("Error", e.printStackTrace().toString())
        }
    }

    override fun getOTP(aMobile: String) {
        try {
            if (mLoginView.checkInternet()) {

                mLoginView.showProgress()

                myRetrofitInstance!!.api.getOTP(aMobile).enqueue(object : Callback<Data> {

                    override fun onResponse(call: Call<Data>, response: Response<Data>) {
                        mLoginView.hideProgress()
                        val aData = response.body()

                        if (aData?.response != null) {
                            val aString = aData.response.response_code
                            if (aString.equals("1")) {
                                val aResponse = aData.response
                                mLoginView.getOTPResponse(aResponse)
                            } else {
                                TIHelper.showAlertDialog(myContext, aData.response.response_message.toString())
                            }

                        }
                    }

                    override fun onFailure(call: Call<Data>, t: Throwable) {
                        mLoginView.hideProgress()
                        TIHelper.showAlertDialog(myContext, "Something went wrong!")
                    }

                })

            } else {
                TIHelper.showAlertDialog(
                    myContext,
                    myContext.resources.getString(R.string.alert_message_check_internet)
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    lateinit var mProductInfoList: List<Product>
    lateinit var mBladeTypesList: List<BladeTypes>
    lateinit var mBladeDescriptionList: List<BladeDescription>
    lateinit var mStateList: List<StateMaster>
    lateinit var mCityList: List<CityMaster>
    lateinit var mAddressList: List<AddressMaster>

    override fun verifyOTP(aParams: HashMap<String, String>) = if (mLoginView.checkInternet()) {

        mLoginView.showProgress()

        myRetrofitInstance!!.api.validateOTP(aParams).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                mLoginView.hideProgress()

                val aData = response.body()

                if (aData?.response != null) {
                    val aString = aData.response.response_code

                    if (aString.equals("1")) {
                        mProductInfoList = aData.getProductList?.Product!!
                        mBladeTypesList = aData.getProductList.BladeTypes!!
                        mBladeDescriptionList = aData.getProductList.BladeDescription!!
                        mStateList = aData.getProductList.StateMaster!!
                        mCityList = aData.getProductList.CityMaster!!
                        mAddressList = aData.getProductList.AddressMaster!!

                        if (mProductInfoList.isNotEmpty()) {
                            mAppDatabase.ProductModel().insertAllProduct(mProductInfoList)
                        }

                        if (mBladeTypesList.isNotEmpty()) {
                            mAppDatabase.bladeTypesDao().insertAllProduct(mBladeTypesList)
                        }

                        if (mBladeDescriptionList.isNotEmpty()) {
                            mAppDatabase.bladeDescriptionDao().insertAllProduct(mBladeDescriptionList)
                        }

                        if (mStateList.isNotEmpty()) {
                            mAppDatabase.stateMasterDao().insertAllState(mStateList)
                        }

                        if (mCityList.isNotEmpty()) {
                            mAppDatabase.cityMasterDao().insertAllCity(mCityList)
                        }

                        if (mAddressList.isNotEmpty()) {
                            mAppDatabase.addressMasterDao().insertAllAddress(mAddressList)
                        }

                        mLoginView.callHomeActivity()

                    } else {
                        TIHelper.showAlertDialog(myContext, aData.response.response_message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                mLoginView.hideProgress()
                TIHelper.showAlertDialog(myContext, "Something went wrong!")
            }

        })
    } else {
        TIHelper.showAlertDialog(
            myContext,
            myContext.resources.getString(R.string.alert_message_check_internet)
        )
    }
}