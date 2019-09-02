package com.aanu.books.manageer.web.controller

import com.aanu.books.manageer.data.DefaultDataCreator
import com.aanu.books.manageer.web.cache.CacheConfiguration
import com.aanu.books.manageer.web.dto.Book
import com.aanu.books.manageer.web.repository.JpaBookRepository
import com.aanu.books.manager.books.model.BookFromPeriodRequest
import com.aanu.books.manager.books.model.BookFromPeriodResponse
import com.aanu.books.manager.books.model.BookWeb
import mu.KLogging
import org.springframework.cache.CacheManager
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class BooksController(
        private val repository: JpaBookRepository,
        private val cacheManager: CacheManager
) {

    private val localBooks = mutableListOf<Book>()


    // @Cacheable("bookByTitle")
    @GetMapping("/get-by/{title}")
    fun getBookByTitle(@PathVariable title: String): Book {
        logger.trace { "Get book by title: $title" }
        return repository.findByTitle(title)
    }

    // @Cacheable("books")
    @GetMapping("/get-all")
    fun getAll(): List<Book> {
        return DefaultDataCreator.createBooksNonFlux(3)
    }

    @GetMapping("/get-books-flux")
    fun getAllFlux(): Flux<Book> {
        logger.info { "Trying to get all books fluxed with thread: ${Thread.currentThread().id}\"" }
        return DefaultDataCreator.createBooks(3)
    }

    @GetMapping("/get-books-flux/{count}")
    fun getCountFlux(@PathVariable count: Int): Flux<Book> {
        logger.info { "Trying to get all books fluxed with thread: ${Thread.currentThread().id}\"" }
        Thread.sleep(1000)
        return DefaultDataCreator.createBooks(count)
    }

    @GetMapping("/get-books-flux-with-variable", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getAllFluxWithVariable(): Flux<Book> {
        logger.info { "Trying to get all books fluxed with creating variable with thread: ${Thread.currentThread().id}" }
        val testVariable = "askdmak"
        return DefaultDataCreator.createBooks(3)
    }

    //@CachePut(value = ["books"])
    @PostMapping("/add-book")
    fun save(@RequestBody book: Book): Book {
        val savedBook = repository.save(book)
        cacheManager.getCache(CacheConfiguration.BOOK_BY_TITLE_CACHE_NAME)?.put(savedBook.title, savedBook)
        logger.debug { "Add book to cache $savedBook" }
        return savedBook
    }

    @PostMapping("/books")
    fun addBook(@RequestBody book: Mono<Book>): Mono<Book> {
        return book.filter { localBooks.add(it) }
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
                ).map { it.toWeb() }
        return BookFromPeriodResponse(books = books)
    }

}

private val logger = KLogging().logger

private fun Book.toWeb(): BookWeb {
    return BookWeb(
            id = id!!,
            title = title,
            creationDate = creationDate,
            size = size.toInt(),
            status = status.toString()
    )
}

