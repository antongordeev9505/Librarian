package com.raywenderlich.android.librarian.database.repository

import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.BooksByGenre
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {
    //hold the code to fetch the data and send it to UI
    //make here any additional operations to the data before send it to the UI

    //use relations of entities for easy
    override suspend fun getBooks(): List<BookAndGenre> = bookDao.getBooks()

    override suspend fun addBook(book: Book) = bookDao.addBook(book)

    override fun getBookById(bookId: String): Book = bookDao.getBookById(bookId)

    override suspend fun removeBook(book: Book) = bookDao.removeBook(book)

    override fun getGenres(): List<Genre> = genreDao.getGenres()

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)

    override fun addGenres(genres: List<Genre>) = genreDao.addGenres(genres)

    override fun addReview(review: Review) = reviewDao.addReview(review)

    override fun updateReview(review: Review) = reviewDao.updateReview(review)

    //use relations of entities for easy
    override fun getReviews(): List<BookReview> = reviewDao.getReviews()

    override fun getReviewsFlow(): Flow<List<BookReview>> = reviewDao.getReviewsFlow()
    //use relations of entities for easy
    override fun getReviewById(reviewId: String): BookReview = reviewDao.getReviewById(reviewId)

    override fun removeReview(review: Review) = reviewDao.removeReview(review)

    override suspend fun addReadingList(readingList: ReadingList) = readingListDao.addReadingList(readingList)

    override suspend fun getReadingList(): List<ReadingListsWithBooks> = readingListDao.getReadingList().map {
        ReadingListsWithBooks(it.id, it.name, emptyList())
    }

    override fun getReadingListFlow(): Flow<List<ReadingListsWithBooks>> = readingListDao.getReadingListFlow().map { items ->
        items.map {
            ReadingListsWithBooks(it.id, it.name, emptyList())
        }
    }

    override suspend fun removeReadingList(readingList: ReadingList) = readingListDao.removeReadingList(readingList)

    override fun getBooksByGenre(genreId: String): List<BookAndGenre> =
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return books.map { BookAndGenre(it, booksByGenre.genre) }
        }

    override fun getBooksByRating(rating: Int): List<BookAndGenre> {
        val reviewsByRating = reviewDao.getReviewsByRating(rating)
        return reviewsByRating.map { BookAndGenre(it.book, genreDao.getGenreById(it.book.genreId)) }
    }
}