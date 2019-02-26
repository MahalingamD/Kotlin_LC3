package com.angler.lc3.ui.contact


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import com.angler.lc3.R
import com.angler.lc3.base.view.BaseFragment
import com.angler.lc3.data.entity.AddressMaster
import com.angler.lc3.data.entity.BladeTypes
import com.angler.lc3.data.entity.CityMaster
import com.angler.lc3.data.entity.StateMaster
import com.angler.lc3.ui.contact.adapter.AddressAdapter
import com.angler.lc3.ui.contact.adapter.CityAdapter
import com.angler.lc3.ui.contact.adapter.StateAdapter
import kotlinx.android.synthetic.main.fragment_contact.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ContactFragment : BaseFragment(), ContactContract.View {


    lateinit var aView: View
    lateinit var aContactPresenter: ContactPresenter

    lateinit var aStateAdapter: StateAdapter
    lateinit var aCityAdapter: CityAdapter
    lateinit var mBladeTypes: BladeTypes

    private lateinit var mSelectedState: String
    private lateinit var mSelectedCity: String

    lateinit var mAddressMasterList: List<AddressMaster>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        aView = inflater.inflate(R.layout.fragment_contact, container, false)

        myContext = this.activity!!

        getBundleValues()

        aContactPresenter = ContactPresenter(this, myContext)


        return aView
    }

    private fun getBundleValues() {

        val aBundle = this.arguments

        mBladeTypes = aBundle?.getSerializable("PassType") as BladeTypes
    }


    private lateinit var myContactAdapter: AddressAdapter

    override fun setUp() {
        showProgress()
        mAddressMasterList = ArrayList<AddressMaster>()

        val layoutManager = LinearLayoutManager(myContext, LinearLayoutManager.VERTICAL, false)
        fragment_contact_recyclerView.layoutManager = layoutManager

        myContactAdapter = AddressAdapter(myContext, mAddressMasterList, mContactInterface)
        fragment_contact_recyclerView.adapter = myContactAdapter

        product_contact_detail.text = mBladeTypes.bladeTypeName
        aContactPresenter.getState()
    }

    override fun onResumeFragment() {

    }


    override fun setState(aStateMasterList: List<StateMaster>) {
        hideProgress()
        aStateAdapter =
            StateAdapter(myContext, R.layout.spinner_rows, aStateMasterList)
        fragment_state_spinner.adapter = aStateAdapter
        fragment_state_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val stateMaster = adapterView.getItemAtPosition(i) as StateMaster
                mSelectedState = stateMaster.stateId
                aContactPresenter.getCity(mSelectedState)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }

    }


    override fun setCity(aCityMasterList: List<CityMaster>) {
        aCityAdapter = CityAdapter(myContext, R.layout.spinner_rows, aCityMasterList)

        fragment_city_spinner.adapter = aCityAdapter
        fragment_city_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(adapterView: AdapterView<*>, view: View?, i: Int, l: Long) {
                val aCityMaster = adapterView.getItemAtPosition(i) as CityMaster
                mSelectedCity = aCityMaster.cityId
                aContactPresenter.getAddress(mSelectedState,mSelectedCity)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    }

    override fun setAddress(aAddressMaster: List<AddressMaster>) {
        mAddressMasterList = aAddressMaster
        if (mAddressMasterList.isNotEmpty()) {
            nodata_layout.visibility = View.GONE
            fragment_contact_recyclerView.visibility = View.VISIBLE
            myContactAdapter.updateAdapter(mAddressMasterList)
        } else {
            nodata_layout.setVisibility(View.VISIBLE)
            fragment_contact_recyclerView.setVisibility(View.GONE)
        }
    }

    val mContactInterface = object : ContactInterface {
        override fun onItemClick(position: Int, aAddressMaster: AddressMaster) {

        }

    }
}
