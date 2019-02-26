package com.angler.lc3.ui.contact

import com.angler.lc3.base.view.MVPView
import com.angler.lc3.data.entity.AddressMaster
import com.angler.lc3.data.entity.CityMaster
import com.angler.lc3.data.entity.StateMaster

class ContactContract {

    interface Presenter {

        fun getState()
        fun getCity(aStateId: String)
        fun getAddress(aStateId: String, aCityId: String)
    }

    interface View : MVPView {

        fun setState(aStateMasterList: List<StateMaster>)

        fun setCity(aCityMasterList: List<CityMaster>)

        fun setAddress(aAddressMaster: List<AddressMaster>)
    }
}