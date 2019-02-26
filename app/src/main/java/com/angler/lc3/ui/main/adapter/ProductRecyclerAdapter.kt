package com.angler.lc3.ui.main.adapter

import android.support.annotation.NonNull
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.data.entity.Product
import com.angler.lc3.helper.TIHelper
import com.angler.lc3.ui.main.RecyclerInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ProductRecyclerAdapter(
    aContext: FragmentActivity,
    aProductList: List<Product>, aViewHeight: Int, aRecyclerInterface: RecyclerInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val TYPE_SINGLE = 1
        private val TYPE_GROUP = 2
    }

    val mContext: FragmentActivity = aContext
    var mProductList: List<Product> = aProductList
    var mRecyclerInterface: RecyclerInterface = aRecyclerInterface
    var mViewHeight: Int = aViewHeight

    private var aDeviceWidth: Int = 0
    private var aAspectRatioHeight: Int = 0
    private var aAspectRatioWidth: Int = 0


    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var aView: View? = null

        val aDisplay = mContext.windowManager.defaultDisplay

        aDeviceWidth = aDisplay.width
        aAspectRatioHeight = aDisplay.height

        aAspectRatioWidth = aDeviceWidth / 2 * 9 / 16 // in this case aspect ratio 16:9
        aAspectRatioHeight = aAspectRatioHeight / 2 - (mViewHeight + 40)

        if (viewType == TYPE_SINGLE) {
            aView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_inflate_product_adapter, parent, false)
            return SingleViewHolder(aView!!)
        } else {
            aView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_inflate_multiple_product_adapter, parent, false)
            return MultipleViewHolder(aView!!)
        }
    }

    override fun getItemCount(): Int {
        return mProductList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mProductList.size == 1 || mProductList.size == 2)
            TYPE_SINGLE
        else
            TYPE_GROUP
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val aProduct: Product = mProductList.get(position)

        when (holder.itemViewType) {
            TYPE_SINGLE -> {
                singleView(holder as SingleViewHolder, aProduct, position)
            }
            TYPE_GROUP -> {
                multipleView(holder as MultipleViewHolder, aProduct, position)
            }
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            mRecyclerInterface.onItemClick(position, aProduct)
        })
    }

    private fun multipleView(ViewHolder: MultipleViewHolder, aProduct: Product, position: Int) {
        ViewHolder.myProductName.text = aProduct.productName
        ViewHolder.myProductSize.visibility = View.GONE
        ViewHolder.myRootView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))

        if (aProduct.productCount == "0") {
            ViewHolder.myProductSize.visibility = View.VISIBLE
            ViewHolder.myProductSize.text = "Coming soon"

        } else {
            ViewHolder.myProductSize.visibility = View.GONE
        }

    }

    private fun singleView(holder: SingleViewHolder, aProduct: Product, position: Int) {

        val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, aAspectRatioHeight)
        layout.gravity = Gravity.CENTER_VERTICAL
        holder.myRootView.layoutParams = layout

        if (position == 0) {
            holder.myProductName.text = aProduct.productName
            holder.myProductSize.visibility = View.GONE
            holder.myRootView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow_green))
        } else {
            holder.myProductName.text = aProduct.productName
            holder.myProductSize.visibility = View.GONE
            holder.myRootView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white))
        }

        loadImage(aProduct, holder.myProductListImg)
    }

    private fun loadImage(aProduct: Product, myProductListImg: ImageView) {
        Glide.with(mContext).load(aProduct.prodImageUrl).apply(
            RequestOptions().placeholder(R.drawable.logo).fitCenter()
        ).into(myProductListImg)
    }


    fun updateAdapter(aProductList: List<Product>) {
        mProductList = aProductList
        notifyDataSetChanged()
    }


    class SingleViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var myProductListImg: ImageView
        internal var myRootView: LinearLayout
        internal var myRootView1: LinearLayout
        internal var myProductName: TextView
        internal var myProductSize: TextView

        init {
            myRootView = itemView.findViewById(R.id.inflate_layout_root_lay)
            myRootView1 = itemView.findViewById(R.id.inflate_layout_root_lay1)
            myProductListImg = itemView.findViewById(R.id.inflate_layout_season_list_img)
            myProductName = itemView.findViewById(R.id.inflate_layout_Product_name_TXT)
            myProductSize = itemView.findViewById(R.id.inflate_layout_Product_Size_TXT)
        }
    }


    class MultipleViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var myProductListImg: ImageView
        internal var myRootView1: LinearLayout
        internal var myRootView: CardView
        internal var myProductName: TextView
        internal var myProductSize: TextView

        init {
            myRootView = itemView.findViewById(R.id.inflate_layout_root_lay)
            myRootView1 = itemView.findViewById(R.id.inflate_layout_root_lay1)
            myProductListImg = itemView.findViewById(R.id.inflate_layout_season_list_img)
            myProductName = itemView.findViewById(R.id.inflate_layout_Product_name_TXT)
            myProductSize = itemView.findViewById(R.id.inflate_layout_Product_Size_TXT)
        }
    }
}