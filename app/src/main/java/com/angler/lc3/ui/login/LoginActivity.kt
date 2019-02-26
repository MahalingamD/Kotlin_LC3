package com.angler.lc3.ui.login

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import com.angler.lc3.R
import com.angler.lc3.base.view.BaseActivity
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.LanguageMaster
import com.angler.lc3.helper.TIHelper
import com.angler.lc3.model.Response
import com.angler.lc3.preference.TIPrefs
import com.angler.lc3.ui.home.HomeActivity
import com.angler.lc3.ui.login.adapter.LanguageAdapter
import com.angler.lc3.utils.MGGpsLocation
import com.tracking.helper.PermissionChecker
import kotlinx.android.synthetic.main.activity_login.*
import java.net.URLEncoder
import java.util.*
import kotlin.collections.ArrayList

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {


    private lateinit var mLoginPresenter: LoginPresenter
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mLanguageMaster: LanguageMaster
    private lateinit var mLanguageList: List<LanguageMaster>
    private lateinit var mAdapter: LanguageAdapter
    private lateinit var appPermissionChecker: PermissionChecker
    private lateinit var list: List<String>
    private lateinit var listarray: Array<String>

    private lateinit var mgGpsLocation: MGGpsLocation

    private lateinit var myLatitudeStr: String

    private lateinit var myLongitudeStr: String

    lateinit var mAPPDatabase: AppDatabase


    override fun attachBaseContext(newBase: Context) {
        var newBase = newBase
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            val aString: String = TIPrefs.getString("sel_language", "")!!
            newBase = TIHelper.changeLang(newBase, aString)
        }
        super.attachBaseContext(newBase)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val aLanguageMaster = TIPrefs.getObject("language", LanguageMaster::class.java)
        if (aLanguageMaster != null) {
            val aLang = firstTwoChar(aLanguageMaster.lan_Name!!)
            setLocale(aLang)
        }

        setContentView(R.layout.activity_login)
        myContext = this@LoginActivity

        mLoginPresenter = LoginPresenter(this, myContext)
        mLoginPresenter.initPresenter()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun firstTwoChar(str: String): String {
        return if (str.length < 2) str else str.substring(0, 2).toLowerCase()
    }

    override fun init() {
        mLanguageList = ArrayList()

        mAPPDatabase = AppDatabase.getDatabase(myContext)

        appPermissionChecker = PermissionChecker()


        listarray = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            appPermissionChecker.askAllPermissions(myContext, listarray)

        setRecyclerAdapter()

        login_get_otp_BUT.setOnClickListener(this)
        login_submit_BUT.setOnClickListener(this)

        mLoginPresenter.getLanguage()
    }

    private fun setRecyclerAdapter() {
        mLinearLayoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        activity_language_recycler.layoutManager = mLinearLayoutManager
        mAdapter = LanguageAdapter(myContext, mLanguageList, mLanguageListener)
        activity_language_recycler.adapter = mAdapter
    }

    private var mPosition: Int = 0

    private lateinit var myLanguageMaster: LanguageMaster

    override fun setLanguageAdapter(mLanguageMaster: List<LanguageMaster>) {
        hideProgress()
        mLanguageList = mAPPDatabase.languageModel().getAllLanguages(1)

        val aLanguage = TIPrefs.getObject("language", LanguageMaster::class.java)
        if (aLanguage != null) {
            for (i in mLanguageList.indices) {
                val bLanguage = mLanguageList.get(i)
                if (bLanguage.lan_id.equals(aLanguage.lan_id)) {
                    bLanguage.isSelect = true
                    myLanguageMaster = aLanguage
                    mPosition = i
                    TIPrefs.putObject("language", myLanguageMaster)
                }
            }
        } else {
            for (i in mLanguageList.indices) {
                val bLanguage = mLanguageList.get(i)
                if (bLanguage.lan_Name!!.toLowerCase().contains("english")) {
                    bLanguage.isSelect = true
                    myLanguageMaster = bLanguage
                    mPosition = i
                    TIPrefs.putObject("language", myLanguageMaster)
                }
            }
        }

        mAdapter.updateAdapter(mLanguageList)

        val handler = Handler()
        handler.postDelayed({ activity_language_recycler.smoothScrollToPosition(mPosition) }, 10)
    }


    override fun getOTPResponse(aResponse: Response) {
        Log.e("OTP", aResponse.otp)
        if (aResponse.response_code.equals("1")) {
            activity_OTP_Layout.visibility = View.VISIBLE
            login_get_otp_BUT.visibility = View.GONE
            login_submit_BUT.visibility = View.VISIBLE
            activity_OTP_EDT.setText(aResponse.otp)
        } else {
            activity_OTP_Layout.visibility = View.GONE
            login_get_otp_BUT.visibility = View.VISIBLE
            login_submit_BUT.visibility = View.GONE
        }
    }


    private val mLanguageListener = object : LanguageListener {
        override fun onLanguageClick(aPosition: Int, aLanguageMaster: LanguageMaster) {
            try {
                mLanguageMaster = aLanguageMaster
                TIPrefs.putObject("language", aLanguageMaster)
                val aLang: String
                when (mLanguageMaster.lan_id) {
                    "1" -> {
                        aLang = firstTwoChar(mLanguageMaster.lan_Name!!)
                        setLocale(aLang)
                    }
                    "2" -> {
                        aLang = firstTwoChar(mLanguageMaster.lan_Name!!)
                        setLocale(aLang)
                    }
                    "3" -> {
                        aLang = firstTwoChar(mLanguageMaster.lan_Name!!)
                        setLocale(aLang)
                    }
                    "4" -> {
                        aLang = firstTwoChar(mLanguageMaster.lan_Name!!)
                        setLocale(aLang)
                    }
                    else -> setLocale("en")
                }

                refresh()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun refresh() {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }


    override fun onClick(view: View?) {

        if (view != null) {
            when (view.id) {
                R.id.login_get_otp_BUT -> {
                    if (appPermissionChecker.checkAllPermission(myContext, listarray)) {
                        val aMobile: String = activity_mobile_EDT.text.toString()
                        validateMobile(aMobile)
                        getGpsDetailInfo()
                    }
                }
                R.id.activity_resend_BTN -> {
                    val aMobile: String = activity_mobile_EDT.text.toString()
                    validateMobile(aMobile)
                }
                R.id.login_submit_BUT -> {
                    validateOTP()
                }
            }
        }

    }


    private fun getGpsDetailInfo() {
        try {
            mgGpsLocation = MGGpsLocation(myContext)
            val manager = myContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                mgGpsLocation = MGGpsLocation(myContext)
                myLatitudeStr = mgGpsLocation.getLatitude().toString()
                myLongitudeStr = mgGpsLocation.getLongitude().toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun validateOTP() {
        val aMobile: String = activity_mobile_EDT.text.toString()
        val aOTP: String = activity_OTP_EDT.text.toString()
        if (aMobile.length == 10) {
            activity_mobile_TXT.isErrorEnabled = false
            if (aOTP.length == 5) {

                mLoginPresenter.verifyOTP(getDeviceDetail())
            } else {
                activity_OTP_TXT.isErrorEnabled = true
                activity_mobile_TXT.error = "enter valid OTP"
            }

        } else {
            activity_mobile_TXT.isErrorEnabled = true
            activity_mobile_TXT.error = "enter valid mobile number"
        }
        getDeviceDetail()
    }

    override fun callHomeActivity() {
        TIPrefs.putBoolean("Login_Status", true)
        startActivity(Intent(this, HomeActivity::class.java))
        this.finish()

    }

    @SuppressLint("MissingPermission")
    private fun getDeviceDetail(): HashMap<String, String> {

        val mDeviceOS = Build.VERSION.SDK_INT
        val mDeviceModel = Build.MODEL
        val mDeviceManufacture = Build.MANUFACTURER
        val mDeviceWidth = TIHelper.getDeviceWidth(myContext)
        val mDeviceHeight = TIHelper.getDeviceHeight(myContext)
        val mDeviceType = TIHelper.isTabletDevice(myContext)

        val telephonyManager: TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        val mDeviceIMEI = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            telephonyManager.imei
        } else {
            telephonyManager.deviceId
        }
        getGpsDetailInfo()

        val params = HashMap<String, String>()
        params["Mobile_Number"] = activity_mobile_EDT.text.toString()
        params["Otp"] = activity_OTP_EDT.text.toString()
        params["dev_OS"] = "" + mDeviceOS
        params["dev_Model"] = URLEncoder.encode(mDeviceModel)
        params["dev_make"] = URLEncoder.encode(mDeviceManufacture)
        params["dev_Size"] = mDeviceWidth + "X" + mDeviceHeight
        params["dev_type"] = mDeviceType
        params["dev_IMEI"] = mDeviceIMEI
        params["Latitude"] = myLatitudeStr
        params["Longitude"] = myLongitudeStr

        return params
    }

    private fun validateMobile(aMobile: String) {
        if (aMobile.length == 10) {
            activity_mobile_TXT.isErrorEnabled = false
            mLoginPresenter.getOTP(aMobile)
        } else {
            activity_mobile_TXT.isErrorEnabled = true
            activity_mobile_TXT.error = "enter valid mobile number"
        }
    }


    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }

    fun setLocale(lang: String) {
        try {
            TIPrefs.putString("sel_language", lang)
            val country = "IN"
            val locale = Locale(lang, country)

            val activityRes = resources
            val activityConf = activityRes.configuration

            activityConf.setLocale(locale)
            activityRes.updateConfiguration(activityConf, activityRes.displayMetrics)

            val applicationRes = applicationContext.resources
            val applicationConf = applicationRes.configuration
            applicationConf.setLocale(locale)
            applicationRes.updateConfiguration(applicationConf, applicationRes.displayMetrics)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
