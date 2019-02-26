package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "address")
class AddressMaster : Serializable {

    @PrimaryKey
    @ColumnInfo(name = "AddressId")
    @SerializedName("AddressId")
    var addressId: String = ""

    @ColumnInfo(name = "AddressName")
    @SerializedName("AddressName")
    var addressName: String? = null

    @ColumnInfo(name = "ConductName")
    @SerializedName("ConductName")
    var conductName: String? = null

    @ColumnInfo(name = "MobileNumber")
    @SerializedName("MobileNumber")
    var mobileNumber: String? = null

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var Active: String? = null

    @ColumnInfo(name = "LanguageId")
    @SerializedName("LanguageId")
    var languageId: String? = null

    @ColumnInfo(name = "CityId")
    @SerializedName("CityId")
    var cityId: String? = null

    @ColumnInfo(name = "StateId")
    @SerializedName("StateId")
    var stateId: String? = null
}
