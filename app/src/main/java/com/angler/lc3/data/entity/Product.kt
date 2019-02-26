package com.angler.lc3.data.entity


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "product")
class Product : Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ProductId")
    @SerializedName("ProductId")
    var productId: String = ""

    @ColumnInfo(name = "ProductName")
    @SerializedName("ProductName")
    var productName: String? = null

    @ColumnInfo(name = "ProdImageUrl")
    @SerializedName("ProdImageUrl")
    var prodImageUrl: String? = null

    @ColumnInfo(name = "LanguageId")
    @SerializedName("LanguageId")
    var languageId: String? = null

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var Active: String? = null

    @ColumnInfo(name = "LastUpdate")
    @SerializedName("LastUpdate")
    var lastUpdate: String? = null

    @ColumnInfo(name = "ProductCount")
    @SerializedName("ProductCount")
    var productCount: String? = null

    @ColumnInfo(name = "ProductLocalPath")
    @SerializedName("ProductLocalPath")
    var productLocalPath: String? = null

    public var isSelect = false


}
