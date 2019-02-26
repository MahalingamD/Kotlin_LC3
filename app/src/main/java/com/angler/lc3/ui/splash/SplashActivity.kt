package com.angler.lc3.ui.splash


import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import com.angler.lc3.BuildConfig
import com.angler.lc3.R
import com.angler.lc3.base.view.BaseActivity
import com.angler.lc3.model.VersionDetails
import com.angler.lc3.preference.TIPrefs
import com.angler.lc3.ui.home.HomeActivity
import com.angler.lc3.ui.login.LoginActivity
import com.angler.lc3.ui.splash.SplashContract.View

class SplashActivity : BaseActivity(), View {


    private lateinit var mSplashPresenter: SplashPresenter

    private val mSplashCount: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        myContext = this@SplashActivity

        mSplashPresenter = SplashPresenter(this, myContext)
        mSplashPresenter.attachView(this)
        mSplashPresenter.initPresenter()

    }


    override fun splashCount() {
        Handler().postDelayed({
            if (checkInternet()) {
                mSplashPresenter.checkVersionDetail()
            } else {
                callNextActivity()
            }
        }, mSplashCount)
    }


    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
    }


    override fun callNextActivity() {
        val intent: Intent
        if (TIPrefs.getBoolean("Login_Status", false))
            intent = Intent(myContext, HomeActivity::class.java)
        else
            intent = Intent(myContext, LoginActivity::class.java)

        startActivity(intent)
        this.finish()
    }

    override fun appUpdate(aVersion: VersionDetails) {
        if (aVersion.versionName != BuildConfig.VERSION_NAME) {
            when (aVersion.status) {
                "1" -> showOptionalDialog(aVersion.message)
                "2" -> showMandatoryDialog(aVersion.message)
                else -> callNextActivity()
            }
        } else {
            callNextActivity()
        }
    }

    private fun showMandatoryDialog(aMessage: String?) {
        try {
            val aBuilder = AlertDialog.Builder(myContext)
            aBuilder.setMessage(aMessage)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setPositiveButton("OK") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }

            val alert = aBuilder.create()
            alert.show()

            val nButton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            nButton.setTextColor(ContextCompat.getColor(myContext, R.color.black))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showOptionalDialog(aMessage: String?) {
        try {
            val aBuilder = AlertDialog.Builder(myContext)
            aBuilder.setMessage(aMessage)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setPositiveButton("Update") { dialogInterface, i ->
                    launchMarket()
                    dialogInterface.dismiss()
                }
                .setNegativeButton("Cancel") { dialogInterface, i ->
                    dialogInterface.dismiss()
                }

            val alert = aBuilder.create()
            alert.show()

            val aPButton = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            aPButton.setTextColor(ContextCompat.getColor(myContext, R.color.black))

            val aNButton = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
            aNButton.setTextColor(ContextCompat.getColor(myContext, R.color.black))

        } catch (e: Exception) {

        }
    }

    private fun launchMarket() {

        val aURI = Uri.parse("market://details?id=" + myContext.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, aURI)
        try {
            startActivity(goToMarket)
            finish()
        } catch (e: ActivityNotFoundException) {
            showToast(getString(R.string.not_launch))
        }


    }
}
