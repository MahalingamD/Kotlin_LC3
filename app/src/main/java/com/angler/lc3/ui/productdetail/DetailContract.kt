package com.angler.lc3.ui.productdetail

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.data.entity.BladeDescription
import com.angler.lc3.data.entity.BladeTypes

class DetailContract {

    interface Presenter {

        fun setProductDetail(aBladeType: String)

        fun getBladeTypes(aProductId: String)

    }

    interface View : MVPView {

        fun setViewPagerAdapter(aProductList: List<BladeDescription>)
        fun setBladeTypeDetails(aBladeTypes: List<BladeTypes>)

    }
}