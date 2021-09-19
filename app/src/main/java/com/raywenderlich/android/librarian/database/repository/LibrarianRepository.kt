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
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks

interface LibrarianRepository {
    //there will be here all the functions which needed to communicate to DB

    //we can combine data - example - return type
    fun getBooks(): List<BookAndGenre>

    fun addBook(book: Book)

    fun getBookById(bookId: String): Book

    fun removeBook(book: Book)

    fun getGenres(): List<Genre>

    fun getGenreById(genreId: String): Genre

    fun addGenres(genres: List<Genre>)

    fun addReview(review: Review)

    fun updateReview(review: Review)

    fun getReviews(): List<BookReview>

    fun getReviewById(reviewId: String): BookReview

    fun removeReview(review: Review)

    fun addReadingList(readingList: ReadingList)

    fun getReadingList(): List<ReadingListsWithBooks>

    fun removeReadingList(readingList: ReadingList)
}