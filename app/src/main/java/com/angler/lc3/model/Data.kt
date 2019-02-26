package com.angler.lc3.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

/**
 * @author mahalingam
 * @date 04-01-2019.
 */
class Data : Serializable {


    @SerializedName("Response")
    val response: Response? = null

    @SerializedName("VersionDetails")
    val versionDetails: VersionDetails? = null

    @SerializedName("GetMasterList")
    val getMasterList: GetMasterList? = null

    @SerializedName("GetProductList")
    val getProductList: GetProductList? = null


}
