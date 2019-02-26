package com.angler.lc3.ui.productType

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.data.entity.BladeTypes

class ProductTypeContract  {

    interface Presenter{

        fun getProductType(productId: String)
    }

    interface View : MVPView{

        fun setProductType(aBladeList: List<BladeTypes>)
    }
}