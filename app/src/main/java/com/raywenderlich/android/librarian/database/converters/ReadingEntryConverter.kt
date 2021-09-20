package com.raywenderlich.android.librarian.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.raywenderlich.android.librarian.App
import com.raywenderlich.android.librarian.model.ReadingEntry

class ReadingEntryConverter {

    @TypeConverter
    fun fromEntries(list: List<ReadingEntry>): String = App.gson.toJson(list)

    //метод парсинга данных
    @TypeConverter
    fun toEntries(json: String): List<ReadingEntry> {
        //создаем typetoken, что бы показать какой тип должен получиться при парсинге
        val listType = object : TypeToken<List<ReadingEntry>>() {}.type

        return try {
            App.gson.fromJson(json, listType)
        } catch (error: Throwable) {
            emptyList()
        }
    }


}