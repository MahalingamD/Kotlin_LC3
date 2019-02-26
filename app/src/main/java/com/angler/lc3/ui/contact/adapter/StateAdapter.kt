package com.angler.lc3.ui.contact.adapter

import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.res.Resources
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.data.entity.StateMaster

class StateAdapter(
    mContext: FragmentActivity,
    inflate_object_name: Int,
    val aStateList: List<StateMaster>
) :
    ArrayAdapter<StateMaster>(mContext, inflate_object_name, aStateList) {

    lateinit var mLayoutInflater: LayoutInflater

    init {
        mLayoutInflater = mContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = mLayoutInflater.inflate(R.layout.spinner_rows, parent, false)
        val label = row.findViewById<TextView>(R.id.inflate_object_name)
        label.text = aStateList.get(position).stateName
        return row
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    override fun getItem(position: Int): StateMaster? {
        return aStateList.get(position)
    }

    override fun getCount(): Int {
        return aStateList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}