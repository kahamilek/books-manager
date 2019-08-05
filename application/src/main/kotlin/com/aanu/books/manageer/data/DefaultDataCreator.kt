package com.aanu.books.manageer.data

import org.springframework.stereotype.Service
import com.aanu.books.manageer.web.dto.Book
import com.aanu.books.manageer.web.controller.BooksController
import kotlin.random.Random

@Service
class DefaultData(
        private val bookController: BooksController
) {

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

    fun getRandomTitle(): String {
        return "book" + (1..10).map { Random.nextInt(0, chars.size / 2) }
                .map { chars::get }
                .joinToString()
    }

}

val chars = ('a'..'z') + ('A'..'Z')