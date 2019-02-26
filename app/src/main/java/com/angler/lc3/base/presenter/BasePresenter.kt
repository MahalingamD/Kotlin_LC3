package com.angler.lc3.base.presenter

import com.angler.lc3.base.view.MVPView

abstract class BasePresenter<V : MVPView> {

    protected var mView: V? = null


    fun BasePresenter(){

    }

    fun attachView(view: V) {
        mView = view
    }


   public fun getView(): V? {
        return mView
    }


    fun detachView() {
        mView = null

    }

    /**
     * Check if the view is attached.
     * This checking is only necessary when returning from an asynchronous call
     */
    protected fun isViewAttached(): Boolean {
        return mView != null
    }

}