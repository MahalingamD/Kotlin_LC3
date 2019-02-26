package com.angler.lc3.ui.productdetail.adapter

import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.data.AppDatabase
import com.angler.lc3.data.entity.BladeTypes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailViewpagerAdapter(
    aContext: FragmentActivity,
    aBladeTypeList: List<BladeTypes>
) : PagerAdapter() {

    val mContext = aContext
    val mBladeTypes = aBladeTypeList

    lateinit var mAppDatabase: AppDatabase
    lateinit var mRecyclerview: RecyclerView
    private lateinit var mRecyclerAdapter: DetailRecyclerViewAdapter

    init {
        mAppDatabase = AppDatabase.getDatabase(mContext)
    }


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflate =
            LayoutInflater.from(container.context).inflate(R.layout.productdetailviewpager_item, container, false)

        val aBladeTypes = mBladeTypes.get(position)

        val aMainImageView = inflate.findViewById<ImageView>(R.id.inflate_product_image)
        val aProductName = inflate.findViewById<TextView>(R.id.inflate_product_name_TXT)

        val aFirstRoottLayout = inflate.findViewById<LinearLayout>(R.id.first_product_root_layout)
        val aSecondRoottLayout = inflate.findViewById<LinearLayout>(R.id.second_product_root_layout)
        val aThirdRoottLayout = inflate.findViewById<LinearLayout>(R.id.third_product_root_layout)

        val aFirst_product_medium_Layout = inflate.findViewById<RelativeLayout>(R.id.first_product_medium_layout)
        val aSecond_product_medium_Layout = inflate.findViewById<RelativeLayout>(R.id.second_product_medium_layout)
        val aThird_product_medium_Layout = inflate.findViewById<RelativeLayout>(R.id.third_product_medium_layout)

        val aFirst_product_small_Layout = inflate.findViewById<RelativeLayout>(R.id.first_product_small_layout)
        val aSecond_product_small_Layout = inflate.findViewById<RelativeLayout>(R.id.second_product_small_layout)
        val aThird_product_small_Layout = inflate.findViewById<RelativeLayout>(R.id.third_product_small_layout)

        val aFirst_product_small_Image = inflate.findViewById<ImageView>(R.id.first_product_small_image)
        val aSecond_product_small_Image = inflate.findViewById<ImageView>(R.id.second_product_small_image)
        val aThird_product_small_Image = inflate.findViewById<ImageView>(R.id.third_product_small_image)

        val aFirst_product_medium_Image = inflate.findViewById<ImageView>(R.id.first_product_medium_image)
        val aSeond_product_medium_Image = inflate.findViewById<ImageView>(R.id.second_product_medium_image)
        val aThird_product_medium_Image = inflate.findViewById<ImageView>(R.id.third_product_medium_image)
        mRecyclerview = inflate.findViewById<RecyclerView>(R.id.myProductDetail_RecyclerView)

        val mLayoutManager: LinearLayoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecyclerview.layoutManager = mLayoutManager


        val aDetailList = mAppDatabase.bladeDescriptionDao().getAllBladeDescriptions(aBladeTypes.bladeTypeId, "1")
        mRecyclerAdapter = DetailRecyclerViewAdapter(mContext, aDetailList)
        mRecyclerview.adapter = mRecyclerAdapter

        aProductName.text = aBladeTypes.bladeTypeName?.toLowerCase()

        loadGlideImage(aBladeTypes.bladeImage1!!, aMainImageView)

        loadGlideImage(aBladeTypes.bladeImage1!!, aFirst_product_small_Image)
        loadGlideImage(aBladeTypes.bladeImage2!!, aSecond_product_small_Image)
        loadGlideImage(aBladeTypes.bladeImage3!!, aThird_product_small_Image)

        loadGlideImage(aBladeTypes.bladeImage1!!, aFirst_product_medium_Image)
        loadGlideImage(aBladeTypes.bladeImage2!!, aSeond_product_medium_Image)
        loadGlideImage(aBladeTypes.bladeImage3!!, aThird_product_medium_Image)


        aFirstRoottLayout.setOnClickListener { view ->

            aFirst_product_medium_Layout.visibility = View.VISIBLE
            aSecond_product_medium_Layout.visibility = View.GONE
            aThird_product_medium_Layout.visibility = View.GONE

            aFirst_product_small_Layout.visibility = View.GONE
            aSecond_product_small_Layout.visibility = View.VISIBLE
            aThird_product_small_Layout.visibility = View.VISIBLE

            loadGlideImage(aBladeTypes.bladeImage1!!, aMainImageView)
        }

        aSecondRoottLayout.setOnClickListener { view ->

            aFirst_product_medium_Layout.visibility = View.GONE
            aSecond_product_medium_Layout.visibility = View.VISIBLE
            aThird_product_medium_Layout.visibility = View.GONE

            aFirst_product_small_Layout.visibility = View.VISIBLE
            aSecond_product_small_Layout.visibility = View.GONE
            aThird_product_small_Layout.visibility = View.VISIBLE

            loadGlideImage(aBladeTypes.bladeImage2!!, aMainImageView)

        }

        aThirdRoottLayout.setOnClickListener { view ->

            aFirst_product_medium_Layout.visibility = View.GONE
            aSecond_product_medium_Layout.visibility = View.GONE
            aThird_product_medium_Layout.visibility = View.VISIBLE

            aFirst_product_small_Layout.visibility = View.VISIBLE
            aSecond_product_small_Layout.visibility = View.VISIBLE
            aThird_product_small_Layout.visibility = View.GONE

            loadGlideImage(aBladeTypes.bladeImage3!!, aMainImageView)
        }


        container.addView(inflate)
        return inflate
    }

    private fun loadGlideImage(Url: String, aImageView: ImageView) {
        Glide.with(mContext).load(Url).apply(
            RequestOptions().placeholder(R.drawable.logo).fitCenter()).into(aImageView)
    }


    override fun isViewFromObject(aView: View, aobject: Any): Boolean {
        return aView == aobject
    }

    override fun getCount(): Int {
        return mBladeTypes.size
    }
}