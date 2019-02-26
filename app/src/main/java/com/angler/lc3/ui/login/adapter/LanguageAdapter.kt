package com.angler.lc3.ui.login.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angler.lc3.R
import com.angler.lc3.data.entity.LanguageMaster
import com.angler.lc3.ui.login.LanguageListener
import kotlinx.android.synthetic.main.language_view_item.view.*


class LanguageAdapter(
    aContext: AppCompatActivity,
    aLanguageMaster: List<LanguageMaster>,
    private val mLanguageListener: LanguageListener
) : RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    val myContext: AppCompatActivity = aContext
    var mLanguageMaster: List<LanguageMaster>

    init {
        mLanguageMaster = aLanguageMaster
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.language_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mLanguageMaster.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val aLanuage = mLanguageMaster.get(position)
        holder.mLan_name.text = aLanuage.lan_Name

        holder.mLan_Layout.setOnClickListener {
            mLanguageListener.onLanguageClick(position, aLanuage)

            for (i in mLanguageMaster.indices) {
                val aLanguageMaster1 = mLanguageMaster.get(i)
                aLanguageMaster1.isSelect = i == position
            }
            notifyDataSetChanged()
        }

        if (aLanuage.isSelect) {
            holder.mLan_Layout.background = ContextCompat.getDrawable(myContext, R.drawable.circle_green_design)
        } else {
            holder.mLan_Layout.background = ContextCompat.getDrawable(myContext, R.drawable.circle_white_design)
        }


    }

    fun updateAdapter(mLanguageList: List<LanguageMaster>) {
        mLanguageMaster = mLanguageList
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mLan_name = itemView.lanuguage_name
        val mLan_Layout = itemView.language_layout
    }
}