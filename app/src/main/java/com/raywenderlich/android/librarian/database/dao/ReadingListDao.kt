package com.raywenderlich.android.librarian.database.dao

import androidx.room.*
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReadingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addReadingList(readingList: ReadingList)

    @Query("SELECT * FROM readingList")
    suspend fun getReadingList(): List<ReadingList>

    @Transaction
    @Query("SELECT * FROM readingList")
    fun getReadingListFlow(): Flow<List<ReadingList>>

    @Delete
    suspend fun removeReadingList(readingList: ReadingList)
}