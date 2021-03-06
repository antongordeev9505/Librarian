package com.raywenderlich.android.librarian.database.dao

import androidx.room.*
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookReview
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Query("SELECT * FROM review")
    fun getReviews(): List<BookReview>

    //reactive way to getting the list of review
    //return the flow of list of BookReview
    @Transaction
    @Query("SELECT * FROM review")
    fun getReviewsFlow(): Flow<List<BookReview>>

    @Delete
    fun removeReview(review: Review)

    @Query("SELECT * FROM review WHERE id = :reviewId")
    fun getReviewById(reviewId: String): BookReview

    @Query("SELECT * FROM review WHERE rating >= :rating")
    fun getReviewsByRating(rating: Int): List<BookReview>
}