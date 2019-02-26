package com.angler.lc3.ui.main

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.data.entity.Product
import com.angler.lc3.model.GetProductList

class MainContract {

    interface Presenter {
        fun initPresenter()
        fun getProduct()

    }

    interface View : MVPView {
        fun setProduct(aProductList: List<Product>)
    }
}