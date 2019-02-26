package com.angler.lc3.ui.contact.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.data.entity.AddressMaster
import com.angler.lc3.ui.contact.ContactInterface

class AddressAdapter(
    mContext: FragmentActivity,
    var mAddressMasterList: List<AddressMaster>,
    val mContactInterface: ContactInterface
) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_view_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mAddressMasterList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aContactDetail = mAddressMasterList[position]
        holder.myContactName.text = aContactDetail.conductName
        holder.myContactAddress.text = aContactDetail.addressName
        holder.myContactMobile.text = aContactDetail.mobileNumber

        holder.myContactLayout.setOnClickListener(View.OnClickListener {
            mContactInterface.onItemClick(position, aContactDetail)
        })
    }

    fun updateAdapter(aAddressMasterList: List<AddressMaster>) {
        mAddressMasterList = aAddressMasterList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val myContactName = itemView.findViewById<TextView>(R.id.contact_name)
        val myContactAddress = itemView.findViewById<TextView>(R.id.contact_address)
        val myContactMobile = itemView.findViewById<TextView>(R.id.contact_mobile)

        val myContactLayout = itemView.findViewById<LinearLayout>(R.id.contact_layout)

    }
}