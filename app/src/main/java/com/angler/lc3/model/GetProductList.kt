package com.angler.lc3.model

import com.angler.lc3.data.entity.*
import com.google.gson.annotations.SerializedName

import java.io.Serializable


class GetProductList : Serializable {


    @SerializedName("Product")
    var Product: List<Product>? = null

    @SerializedName("BladeTypes")
    var BladeTypes: List<BladeTypes>? = null

    @SerializedName("BladeDescription")
    var BladeDescription: List<BladeDescription>? = null

    @SerializedName("LanguageMaster")
    var mLanguageMaster: List<LanguageMaster>? = null


    @SerializedName("StateMaster")
    var StateMaster: List<StateMaster>? = null


    @SerializedName("CityMaster")
    var CityMaster: List<CityMaster>? = null

    @SerializedName("AddressMaster")
    var AddressMaster: List<AddressMaster>? = null


}
