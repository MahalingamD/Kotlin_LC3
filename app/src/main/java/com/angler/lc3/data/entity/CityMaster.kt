package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "city")
class CityMaster : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "CityId")
    @SerializedName("CityId")
    var cityId: String = ""

    @ColumnInfo(name = "CityName")
    @SerializedName("CityName")
    var cityName: String = ""

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var Active: String = ""

    @ColumnInfo(name = "LanguageId")
    @SerializedName("LanguageId")
    var languageId: String = ""

    @ColumnInfo(name = "StateId")
    @SerializedName("StateId")
    var stateId: String = ""
}
