package com.raywenderlich.android.librarian.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raywenderlich.android.librarian.database.converters.DateConverter
import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review

const val DATABASE_VERSION = 1

//create DB
@Database(
    //list of entities
    entities = [
        Book::class,
        Genre::class,
        Review::class,
        ReadingList::class],
    //version
    version = DATABASE_VERSION
)

//if converter will be needed - use this
@TypeConverters(DateConverter::class)
abstract class LibrarianDatabase: RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "Librarian"

        fun buildDatabase(context: Context): LibrarianDatabase {
            //use builder to create DB
            return Room.databaseBuilder(
                context,
                //db type
                LibrarianDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    //provide daos - bind dao to the DB
    abstract fun bookDao(): BookDao

    abstract fun genreDao(): GenreDao

    abstract fun reviewDao(): ReviewDao

    abstract fun readingListDao(): ReadingListDao

}