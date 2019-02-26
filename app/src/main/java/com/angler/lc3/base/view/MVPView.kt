package com.angler.lc3.base.view

interface MVPView {
    fun showProgress()
    fun hideProgress();
    fun checkInternet(): Boolean
    fun showToast(aMessage: String)

}