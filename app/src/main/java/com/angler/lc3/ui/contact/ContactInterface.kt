package com.angler.lc3.ui.contact

import com.angler.lc3.data.entity.AddressMaster

interface ContactInterface {

    fun onItemClick(position: Int, aAddressMaster: AddressMaster)
}