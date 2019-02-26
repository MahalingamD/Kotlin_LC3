package com.angler.lc3.ui.login

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.data.entity.LanguageMaster
import com.angler.lc3.model.Response

class LoginContract {
    interface Presenter {

        fun initPresenter()
        fun getLanguage()
        fun getOTP(aMobile: String)
        fun verifyOTP(aParams: HashMap<String, String>)
    }

    interface View : MVPView {
        fun init()
        fun setLanguageAdapter(mLanguageMaster: List<LanguageMaster>)
        fun getOTPResponse(aResponse: Response)
        fun callHomeActivity()
    }
}