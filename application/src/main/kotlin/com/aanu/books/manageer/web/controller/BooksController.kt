package com.aanu.books.manageer.web.controller

import mu.KLogging
import org.springframework.cache.CacheManager
import org.springframework.web.bind.annotation.*
import com.aanu.books.manageer.web.cache.CacheConfiguration
import com.aanu.books.manageer.web.dto.Book
import com.aanu.books.manageer.web.repository.JpaBookRepository
import com.aanu.books.manager.books.model.BookFromPeriodRequest
import com.aanu.books.manager.books.model.BookFromPeriodResponse
import com.aanu.books.manager.books.model.BookWeb

@RestController
class BooksController(
        private val repository: JpaBookRepository,
        private val cacheManager: CacheManager
) {

    companion object : KLogging()

   // @Cacheable("bookByTitle")
    @GetMapping("/get-by/{title}")
    fun getBookByTitle(@PathVariable title: String): Book {
       logger.trace { "Get book by title: $title" }
        return repository.findByTitle(title)
    }

   // @Cacheable("books")
    @GetMapping("/get-all")
    fun getAll(): List<Book> {
        logger.warn { "Find all books: ${repository.findAll()}" }
        return repository.findAll()
    }

    //@CachePut(value = ["books"])
    @PostMapping("/add-book")
    fun save(@RequestBody book: Book): Book {
        val savedBook = repository.save(book)
        cacheManager.getCache(CacheConfiguration.BOOK_BY_TITLE_CACHE_NAME)?.put(savedBook.title, savedBook)
        logger.debug { "Add book to cache $savedBook" }
        return savedBook
    }

    @GetMapping("/books-by")
    fun getStartingWith(@RequestParam title: String): Book {
        logger.info { "Trying extract by equals title with: $title" }
        return repository.findByTitleEquals(title)
    }

    @PostMapping("/api/books/v1/from-period")
    fun getBooksForPeriod(@RequestBody request: BookFromPeriodRequest): BookFromPeriodResponse {
        val books = repository
                .findAllByCreationDateBetween(
                        request.fromDateInclusive,
                        request.throughDateInclusive
                ).map{ it.toWeb()}
        return BookFromPeriodResponse(books = books )
    }

}

private fun Book.toWeb(): BookWeb {
    return BookWeb(
            id = id!!,
            title = title,
            creationDate = creationDate,
            size = size.toInt(),
            status = status.toString()
    )
}

