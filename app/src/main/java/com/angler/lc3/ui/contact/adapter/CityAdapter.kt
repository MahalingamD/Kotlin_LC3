package com.angler.lc3.ui.contact.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.angler.lc3.R
import com.angler.lc3.data.entity.CityMaster

class CityAdapter(val mContext: Context, resource: Int, val aCityMaster: List<CityMaster>) :
    ArrayAdapter<CityMaster>(mContext, resource, aCityMaster) {

    lateinit var mInflater: LayoutInflater

    init {
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        val row = mInflater.inflate(R.layout.spinner_rows, parent, false)
        val label = row.findViewById<TextView>(R.id.inflate_object_name)
        label.text = aCityMaster.get(position).cityName
        return row
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }



    override fun getItem(position: Int): CityMaster? {
        return aCityMaster[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return aCityMaster.size
    }
}