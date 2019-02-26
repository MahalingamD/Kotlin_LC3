package com.angler.lc3.ui.home

import com.angler.lc3.base.view.MVPView

class HomeContract {

    interface Presenter{
        fun initPresenter()
    }

    interface View : MVPView{

        fun initActivity()
        fun callDefaultFragment()

    }
}