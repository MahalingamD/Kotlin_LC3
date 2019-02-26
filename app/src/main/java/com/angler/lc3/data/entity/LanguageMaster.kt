package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "language")
class LanguageMaster : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "LanguageId")
    @SerializedName("Language_id")
    var lan_id: String = ""

    @ColumnInfo(name = "LanguageName")
    @SerializedName("Language_name")
    var lan_Name: String? = null

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var active = 0

    var isSelect = false

    fun isActive(): Int {
        return active
    }


}
