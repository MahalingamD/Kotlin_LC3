package com.angler.lc3.ui.productType

import com.angler.lc3.data.entity.BladeTypes

interface ProductInterface {

    fun onItemClick(position: Int, aBladeTypes: BladeTypes)
}