package com.angler.lc3.ui.productdetail.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angler.lc3.R
import com.angler.lc3.data.entity.BladeDescription
import kotlinx.android.synthetic.main.product_item_view_item.view.*

class DetailRecyclerViewAdapter(
    val mContext: FragmentActivity,
    val aDetailList: List<BladeDescription>
) : RecyclerView.Adapter<DetailRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val view = layoutInflater.inflate(R.layout.product_item_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return aDetailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aBladeDescription: BladeDescription = aDetailList.get(position)
        holder.mProduct_Type.text = aBladeDescription.bladeDesc
        holder.mProduct_Name.text = aBladeDescription.rotogroValues
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mProduct_Type = itemView.inflate_product_type
        val mProduct_Name = itemView.inflate_product_name
    }
}