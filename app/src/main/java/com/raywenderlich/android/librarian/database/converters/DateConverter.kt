package com.raywenderlich.android.librarian.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    //get primitive and translate to data object to review class holds
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return Date(value ?: 0)
    }

    //opposite - get complex object and translate to primitive for db
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long = date?.time?: 0
}