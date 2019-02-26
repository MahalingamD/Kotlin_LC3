package com.angler.lc3.ui.productType.adapter

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.base.fragmentmanger.APPFragmentManager
import com.angler.lc3.data.entity.BladeTypes
import com.angler.lc3.data.entity.Product
import com.angler.lc3.ui.productdetail.ProductDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProductViewPagerAdapter(
    aContext: FragmentActivity,
    aBladeList: List<BladeTypes>,
    aProduct: Product
) : PagerAdapter() {

    val mContext: FragmentActivity = aContext
    val mBladeList: List<BladeTypes> = aBladeList
    val mProduct = aProduct
    lateinit var mAPPFragmentManager: APPFragmentManager

    init {
        mAPPFragmentManager = APPFragmentManager(mContext)
    }

    override fun isViewFromObject(view: View, aObject: Any): Boolean {
        return view == aObject
    }

    override fun getCount(): Int {
        return mBladeList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate: View =
            LayoutInflater.from(container.context).inflate(R.layout.cardviewpager_item, container, false)

        val aProductName: TextView = inflate.findViewById(R.id.inflate_product_name_TXT)
        val aProductRate: TextView = inflate.findViewById(R.id.inflate_product_rate_TXT)
        val aProductPosition: TextView = inflate.findViewById(R.id.inflate_item_current_position_TXT)
        val aTotalCount: TextView = inflate.findViewById(R.id.inflate_item_total_position_TXT)
        val aProductImage: ImageView = inflate.findViewById(R.id.inflate_product_image)
        val aProductLayout: LinearLayout = inflate.findViewById(R.id.inflate_product_detail_Layout)

        val aBladeTypes: BladeTypes = mBladeList.get(position)

        aProductName.text = aBladeTypes.bladeTypeName?.toLowerCase()
        aProductPosition.text = (position + 1).toString()
        aTotalCount.text = "/" + mBladeList.size.toString()

        loadImage(aBladeTypes, aProductImage)

        aProductLayout.setOnClickListener(View.OnClickListener {

            val aBundle = Bundle()

            aBundle.putInt("position", position)
            aBundle.putSerializable("bladeType", aBladeTypes)
            aBundle.putSerializable("product", mProduct)

            mAPPFragmentManager.updateContent(ProductDetailFragment(), "", aBundle)
        })

        container.addView(inflate)
        return inflate
    }


    fun loadImage(aProduct: BladeTypes, aImageView: ImageView) {
        Glide.with(mContext).load(aProduct.bladeImage1).apply(
            RequestOptions().placeholder(R.drawable.logo).fitCenter()
        ).into(aImageView)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}