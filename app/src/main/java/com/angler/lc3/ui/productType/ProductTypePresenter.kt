package com.angler.lc3.ui.productType

import android.support.v4.app.FragmentActivity
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.BladeTypes

class ProductTypePresenter(
    aproductTypeFragment: ProductTypeFragment,
    aContext: FragmentActivity
) : BasePresenter<ProductTypeContract.View>(), ProductTypeContract.Presenter {


    val mProductType: ProductTypeContract.View = aproductTypeFragment
    val myContext: FragmentActivity = aContext
    lateinit var mAppDatabase: AppDatabase

    init {
        mAppDatabase = AppDatabase.getDatabase(myContext)
    }


    override fun getProductType(productId: String) {
        val aBladeList: List<BladeTypes> = mAppDatabase.bladeTypesDao().getAllProducts(productId, "1")
        mProductType.setProductType(aBladeList)
    }
}