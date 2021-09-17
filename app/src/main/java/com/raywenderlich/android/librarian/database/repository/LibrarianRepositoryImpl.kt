package com.raywenderlich.android.librarian.database.repository

import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.relations.BookAndGenre

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {
    //hold the code to fetch the data and send it to UI
    //make here any additional operations to the data before send it to the UI

    //combine 2 daos
    override fun getBooks(): List<BookAndGenre> = bookDao.getBooks().map {
        BookAndGenre(it, genreDao.getGenreById(it.genreId))
    }

    override fun getGenres(): List<Genre> = genreDao.getGenres()

    override fun addBook(book: Book) = bookDao.addBook(book)

    override fun getGenreById(genreId: String): Genre = genreDao.getGenreById(genreId)
}