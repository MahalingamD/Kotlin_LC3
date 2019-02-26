package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "bladedescription")
class BladeDescription : Serializable {


    @PrimaryKey
    @ColumnInfo(name = "BladeDescId")
    @SerializedName("BladeDescId")
    var bladeDescId: String = ""

    @ColumnInfo(name = "BladeTypeId")
    @SerializedName("BladeTypeId")
    var bladeTypeId: String? = null

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var Active: String? = null

    @ColumnInfo(name = "BladeDesc")
    @SerializedName("BladeDesc")
    var bladeDesc: String? = null

    @ColumnInfo(name = "RotogroValues")
    @SerializedName("RotogroValues")
    var rotogroValues: String? = null
}
