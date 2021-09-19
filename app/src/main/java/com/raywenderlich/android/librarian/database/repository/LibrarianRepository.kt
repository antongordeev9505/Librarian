package com.raywenderlich.android.librarian.database.repository

import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.relations.BookAndGenre

interface LibrarianRepository {
    //there will be here all the functions which needed to communicate to DB

    //we can combine data - example - return type
    fun getBooks(): List<BookAndGenre>

    fun getGenres(): List<Genre>

    fun addBook(book: Book)

    fun getGenreById(genreId: String): Genre

    fun addGenres(genres: List<Genre>)
}