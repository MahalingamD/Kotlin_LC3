package com.angler.lc3.model

import com.angler.lc3.data.entity.LanguageMaster
import com.google.gson.annotations.SerializedName

class GetMasterList {


    @SerializedName("LanguageMaster")
    var languageMaster: List<LanguageMaster>? = null

}
