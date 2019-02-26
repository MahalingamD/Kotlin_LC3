package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "bladetypes")
class BladeTypes : Serializable {


    @ColumnInfo(name = "ProductId")
    @SerializedName("ProductId")
    var productId: String? = ""

    @PrimaryKey
    @ColumnInfo(name = "BladeTypeId")
    @SerializedName("BladeTypeId")
    var bladeTypeId: String = ""

    @ColumnInfo(name = "BladeTypeName")
    @SerializedName("BladeTypeName")
    var bladeTypeName: String? = ""

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var Active: String? = ""

    @ColumnInfo(name = "BladeImage1")
    @SerializedName("BladeImage1")
    var bladeImage1: String? = ""

    @ColumnInfo(name = "BladeImage2")
    @SerializedName("BladeImage2")
    var bladeImage2: String? = ""

    @ColumnInfo(name = "BladeImage3")
    @SerializedName("BladeImage3")
    var bladeImage3: String? = ""

    @ColumnInfo(name = "LocalBladeImage1")
    @SerializedName("LocalBladeImage1")
    var localBladeImage1: String? = ""

    @ColumnInfo(name = "LocalBladeImage2")
    @SerializedName("LocalBladeImage2")
    var localBladeImage2: String? = ""

    @ColumnInfo(name = "LocalBladeImage3")
    @SerializedName("LocalBladeImage3")
    var localBladeImage3: String? = ""

    @ColumnInfo(name = "Rate")
    @SerializedName("Rate")
    var rate: String? = ""


}
