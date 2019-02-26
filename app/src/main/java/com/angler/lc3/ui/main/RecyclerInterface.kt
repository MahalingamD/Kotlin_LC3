package com.angler.lc3.ui.main

import com.angler.lc3.data.entity.Product

interface RecyclerInterface {
    fun onItemClick(position: Int, aProduct: Product)
}