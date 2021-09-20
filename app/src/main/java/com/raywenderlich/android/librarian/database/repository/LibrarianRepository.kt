package com.raywenderlich.android.librarian.database.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.BooksByGenre
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks

interface LibrarianRepository {
    //there will be here all the functions which needed to communicate to DB

    //we can combine data - example - return type
    suspend fun getBooks(): List<BookAndGenre>

    suspend fun addBook(book: Book)

    fun getBookById(bookId: String): Book

    suspend fun removeBook(book: Book)

    fun getGenres(): List<Genre>

    fun getGenreById(genreId: String): Genre

    fun addGenres(genres: List<Genre>)

    fun addReview(review: Review)

    fun updateReview(review: Review)

    fun getReviews(): List<BookReview>

    fun getReviewById(reviewId: String): BookReview

    fun removeReview(review: Review)

    suspend fun addReadingList(readingList: ReadingList)

    suspend fun getReadingList(): List<ReadingListsWithBooks>

    suspend fun removeReadingList(readingList: ReadingList)

    fun getBooksByGenre(genreId: String): List<BookAndGenre>

    fun getBooksByRating(rating: Int): List<BookAndGenre>
}