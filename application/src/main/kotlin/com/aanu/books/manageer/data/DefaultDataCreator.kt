package com.aanu.books.manageer.data

import com.aanu.books.manageer.web.controller.BooksController
import com.aanu.books.manageer.web.dto.Book
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import kotlin.random.Random

@Service
class DefaultDataCreator(
        private val bookController: BooksController
) {
    companion object {

        val chars = ('a'..'z') + ('A'..'Z')

        fun createBooks(countOfBooks: Int): Flux<Book> {
            return (1..countOfBooks)
                    .map {
                        Book(
                                id = it.toLong(),
                                title = getRandomTitle(),
                                size = Random.nextInt(20, 100).toLong()
                        )
                    }.toFlux()
        }

        fun getRandomTitle(): String {
            return "book:" + (1..10)
                    .map { Random.nextInt(0, chars.size) }
                    .map { chars[it] }
                    .joinToString("")
        }

        fun createBooksNonFlux(countOfBooks: Int): List<Book> {
            return (1..countOfBooks)
                    .map {
                        Book(
                                id = it.toLong(),
                                title = getRandomTitle(),
                                size = Random.nextInt(20, 100).toLong()
                        )
                    }
        }
    }

    fun createData() {
        for (i in 1..100) {
            bookController.save(
                    Book(
                            id = i.toLong(),
                            title = getRandomTitle(),
                            size = Random.nextInt(20, 100).toLong()
                    )
            )
        }
    }


}

