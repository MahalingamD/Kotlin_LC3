package com.angler.lc3.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


class VersionDetails : Serializable {

    @SerializedName("Message")
    var message: String? = null
    @SerializedName("Response")
    var response: Response? = null
    @SerializedName("Status")
    var status: String? = null
    @SerializedName("Update_type")
    var updateType: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("VersionDetails")
    var versionDetails: VersionDetails? = null
    @SerializedName("Version_name")
    var versionName: String? = null

}
