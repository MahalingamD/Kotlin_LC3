package com.angler.lc3.ui.main

import android.content.res.Resources
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.angler.lc3.R
import com.angler.lc3.base.fragmentmanger.APPFragmentManager
import com.angler.lc3.base.view.BaseFragment
import com.angler.lc3.data.entity.Product
import com.angler.lc3.ui.main.adapter.ProductRecyclerAdapter
import com.angler.lc3.ui.productType.ProductTypeFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(), MainContract.View {

    lateinit var aView: View
    lateinit var mMainPresenter: MainPresenter

    lateinit var mLayoutManager: LinearLayoutManager

    lateinit var mProductAdapter: ProductRecyclerAdapter
    lateinit var mProductList: List<Product>

    lateinit var mAPPFragmentManager: APPFragmentManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.fragment_main, container, false)

        myContext = activity!!

        return aView
    }


    override fun setUp() {
        mAPPFragmentManager = APPFragmentManager(myContext)
        mMainPresenter = MainPresenter(this, myContext)
        mMainPresenter.initPresenter()

        mLayoutManager = LinearLayoutManager(myContext)
        // fragment_main_product_RECYCLE.layoutManager = mLayoutManager

        mProductList = ArrayList<Product>()

        getViewHeight()
        configRecycler()
        mMainPresenter.getProduct()
    }


    private fun configRecycler() {
        val layoutManager = GridLayoutManager(myContext, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val aSize = mProductList.size
                if (aSize == 1)
                    return 2
                else if (aSize == 2)
                    return 2
                else
                    return 1
            }
        }
        fragment_main_product_RECYCLE.layoutManager = layoutManager
        mProductAdapter = ProductRecyclerAdapter(myContext, mProductList, viewHeight, mRecyclerInterface)
        fragment_main_product_RECYCLE.adapter = mProductAdapter
        fragment_main_product_RECYCLE.setNestedScrollingEnabled(false)
        fragment_main_product_RECYCLE.setHasFixedSize(true)
    }


    override fun setProduct(aProductList: List<Product>) {
        mProductList = aProductList
        mProductAdapter.updateAdapter(mProductList)
    }


    private var viewHeight: Int = 0

    fun getViewHeight() {
        try {
            // status bar height
            var statusBarHeight = 0
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                statusBarHeight = resources.getDimensionPixelSize(resourceId)
            }

            // action bar height
            var actionBarHeight = 0
            val styledAttributes = activity!!.getTheme().obtainStyledAttributes(
                intArrayOf(android.R.attr.actionBarSize)
            )
            actionBarHeight = styledAttributes.getDimension(0, 0f).toInt()
            styledAttributes.recycle()

            viewHeight = statusBarHeight + actionBarHeight

            viewHeight /= 2
        } catch (e: Resources.NotFoundException) {
            e.printStackTrace()
        }

    }


    val mRecyclerInterface: RecyclerInterface = object : RecyclerInterface {
        override fun onItemClick(position: Int, aProduct: Product) {
            showToast(aProduct.productName.toString())

            val aBundle = Bundle()
            aBundle.putSerializable("product", aProduct)
            mAPPFragmentManager.updateContent(ProductTypeFragment(), "", aBundle)
        }
    }


    override fun onResumeFragment() {

    }

}
