package com.angler.lc3.data.converter

import android.arch.persistence.room.TypeConverter

import java.util.Date

internal object DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
