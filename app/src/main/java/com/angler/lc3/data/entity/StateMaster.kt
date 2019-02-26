package com.angler.lc3.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

import java.io.Serializable

@Entity(tableName = "state")
class StateMaster : Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "StateId")
    @SerializedName("StateId")
    var stateId: String = ""

    @ColumnInfo(name = "StateName")
    @SerializedName("StateName")
    var stateName: String? = null

    @ColumnInfo(name = "IsActive")
    @SerializedName("IsActive")
    var active: String? = null

    @ColumnInfo(name = "LanguageId")
    @SerializedName("LanguageId")
    var languageId: String? = null
}
