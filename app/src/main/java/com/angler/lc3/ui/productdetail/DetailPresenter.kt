package com.angler.lc3.ui.productdetail

import android.support.v4.app.FragmentActivity
import com.angler.lc3.base.presenter.BasePresenter
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.BladeTypes

class DetailPresenter(
    aDetailFragment: ProductDetailFragment,
    aContext: FragmentActivity
) : BasePresenter<DetailContract.View>(), DetailContract.Presenter {


    val mDetailFragmentView: DetailContract.View = aDetailFragment
    private val mCotext: FragmentActivity = aContext

    lateinit var mAppDatabase: AppDatabase

    init {
        mAppDatabase = AppDatabase.getDatabase(mCotext)
    }


    override fun setProductDetail(aBladeType: String) {
        val aBladeDescriptions = mAppDatabase.bladeDescriptionDao().getAllBladeDescriptions(aBladeType, "1")
        mDetailFragmentView.setViewPagerAdapter(aBladeDescriptions)
    }

    override fun getBladeTypes(aProductId: String) {

        val aBladeList: List<BladeTypes> = mAppDatabase.bladeTypesDao().getAllProducts(aProductId, "1")
        mDetailFragmentView.setBladeTypeDetails(aBladeList)

    }

}