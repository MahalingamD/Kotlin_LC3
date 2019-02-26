package com.angler.lc3.ui.splash

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.model.VersionDetails

class SplashContract {
    public interface Presenter {

        fun initPresenter()
        fun checkVersionDetail()

    }

    public interface View : MVPView {

        fun splashCount()
        fun callNextActivity()
        fun appUpdate(aVersion: VersionDetails)
    }
}