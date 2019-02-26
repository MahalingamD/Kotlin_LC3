package com.angler.lc3.ui.productType


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.angler.lc3.R
import com.angler.lc3.base.fragmentmanger.APPFragmentManager
import com.angler.lc3.base.view.BaseFragment
import com.angler.lc3.data.entity.BladeTypes
import com.angler.lc3.data.entity.Product
import com.angler.lc3.ui.productType.adapter.ProductViewPagerAdapter
import com.angler.lc3.utils.RotateDownPageTransformer
import kotlinx.android.synthetic.main.fragment_product_type.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ProductTypeFragment : BaseFragment(), ProductTypeContract.View {

    lateinit var mAPPFragmentManager: APPFragmentManager

    lateinit var mProductTypePresenter: ProductTypePresenter

    lateinit var aView: View
    lateinit var mProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.fragment_product_type, container, false)

        myContext = activity!!

        getBundleValue()
        return aView
    }

    private fun getBundleValue() {
        val aBundle: Bundle = this.arguments!!
        mProduct = aBundle.getSerializable("product") as Product
        showToast("Detail" + mProduct.productName.toString())
    }


    override fun setUp() {
        mProductTypePresenter = ProductTypePresenter(this, myContext)
        mProductTypePresenter.getProductType(mProduct.productId)

        viewpager.offscreenPageLimit = 3
        viewpager.pageMargin = 40


    }

    override fun setProductType(aBladeList: List<BladeTypes>) {

        if (aBladeList.isNotEmpty()) {
            val aViewAdapter: ProductViewPagerAdapter = ProductViewPagerAdapter(myContext, aBladeList,mProduct)
            viewpager.adapter = aViewAdapter
            viewpager.pageMargin = 40
            viewpager.setPageTransformer(true, RotateDownPageTransformer())

        } else {

        }
    }

    override fun onResumeFragment() {

    }


}
