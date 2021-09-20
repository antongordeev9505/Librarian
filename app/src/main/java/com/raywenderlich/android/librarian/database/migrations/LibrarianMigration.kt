package com.raywenderlich.android.librarian.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//migrate from version 1 to 2
val migration_1_2 = object : Migration(1, 2) {
    //override migrate function - how ti migrate data
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE review ADD COLUMN lastUpdatedDate INTEGER NOT NULL DEFAULT 0")
    }
}