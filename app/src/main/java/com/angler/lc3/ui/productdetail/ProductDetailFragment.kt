package com.angler.lc3.ui.productdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.angler.lc3.R
import com.angler.lc3.base.fragmentmanger.APPFragmentManager
import com.angler.lc3.base.view.BaseFragment
import com.angler.lc3.data.entity.BladeDescription
import com.angler.lc3.data.entity.BladeTypes
import com.angler.lc3.data.entity.Product
import com.angler.lc3.ui.contact.ContactFragment
import com.angler.lc3.ui.productdetail.adapter.DetailViewpagerAdapter
import kotlinx.android.synthetic.main.fragment_product_detail.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ProductDetailFragment : BaseFragment(), DetailContract.View, View.OnClickListener {


    lateinit var aView: View
    lateinit var mDetailPresenter: DetailPresenter
    lateinit var mAPPFragmentManager: APPFragmentManager

    lateinit var mBladeTypeList: List<BladeTypes>
    lateinit var mBladeDescription: List<BladeDescription>

    lateinit var mDetailViewPagerAdapter: DetailViewpagerAdapter

    var passPosition: Int = 0
    lateinit var mProduct: Product
    lateinit var mBladeTypes: BladeTypes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.fragment_product_detail, container, false)
        myContext = this.activity!!

        mDetailPresenter = DetailPresenter(this, myContext)


        getBundleValues()

        return aView
    }

    private fun getBundleValues() {
        val aBundle = this.arguments

        val aPosition = aBundle?.getInt("position")
        passPosition = aPosition!!
        mBladeTypes = aBundle.getSerializable("bladeType") as BladeTypes
        mProduct = aBundle.getSerializable("product") as Product

        print(aPosition)

    }


    override fun setUp() {
        mAPPFragmentManager = APPFragmentManager(myContext)




        product_detail_viewpager.offscreenPageLimit = 1
        product_detail_viewpager.pageMargin = 40

        fragment_page_left_image.setOnClickListener(this)
        fragment_page_right_image.setOnClickListener(this)
        fragment_page_call_Text.setOnClickListener(this)

        mDetailPresenter.getBladeTypes(mProduct.productId)
        mDetailPresenter.setProductDetail(mBladeTypes.bladeTypeId)


    }


    override fun setViewPagerAdapter(aProductList: List<BladeDescription>) {
        mBladeDescription = aProductList

        mDetailViewPagerAdapter = DetailViewpagerAdapter(myContext, mBladeTypeList)
        product_detail_viewpager.adapter = mDetailViewPagerAdapter
        product_detail_viewpager.currentItem = passPosition

        product_detail_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {

            }

            override fun onPageScrolled(position: Int, v: Float, position1: Int) {

            }

            override fun onPageSelected(position: Int) {
                passPosition = position
                mBladeTypes = mBladeTypeList.get(position)
                setNavigation(passPosition)
            }

        })

    }

    private fun setNavigation(aPosition: Int) {

        if (aPosition == 0) {
            DrawableCompat.setTint(
                fragment_page_left_image.drawable,
                ContextCompat.getColor(myContext, R.color.light_gray)
            )
            fragment_page_left_image.isEnabled = false
        } else {
            DrawableCompat.setTint(fragment_page_left_image.drawable, ContextCompat.getColor(myContext, R.color.black))
            fragment_page_left_image.isEnabled = true
        }

        if (passPosition == mBladeTypeList.size - 1) {
            DrawableCompat.setTint(
                fragment_page_right_image.drawable,
                ContextCompat.getColor(myContext, R.color.light_gray)
            )
            fragment_page_right_image.isEnabled = false
        } else {
            DrawableCompat.setTint(fragment_page_right_image.drawable, ContextCompat.getColor(myContext, R.color.black))
            fragment_page_right_image.isEnabled = true
        }

    }

    override fun setBladeTypeDetails(aBladeTypes: List<BladeTypes>) {
        mBladeTypeList = aBladeTypes
    }

    override fun onResumeFragment() {

    }


    override fun onClick(aView: View?) {

        when (aView!!.id) {
            R.id.fragment_page_left_image -> {
                product_detail_viewpager.currentItem = passPosition - 1
            }
            R.id.fragment_page_right_image -> {
                product_detail_viewpager.currentItem = passPosition + 1
            }
            R.id.fragment_page_call_Text -> {
                val aBundle = Bundle()
                aBundle.putSerializable("PassType", mBladeTypes)
                mAPPFragmentManager.updateContent(ContactFragment(), "", aBundle)
            }
        }
    }

}
