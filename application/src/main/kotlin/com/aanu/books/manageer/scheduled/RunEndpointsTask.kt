package com.aanu.books.manageer.scheduled

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import com.aanu.books.manageer.data.DefaultDataCreator
import com.aanu.books.manageer.web.dto.Book
import com.aanu.books.manageer.web.controller.BooksController
import mu.KLogging

@Controller
class RunEndpointsTask(
        private val bookController: BooksController,
        private val defaultDataCreator: DefaultDataCreator
) {

    val CONST_BOOK = Book(
            title = DefaultDataCreator.getRandomTitle(),
            size = 5
    )

    @Scheduled(fixedDelay = 10_000)
    fun runAllEndpointsFixed5s() {
        logger.info { "Jestem w nowym wątku: ${Thread.currentThread().name}"}
//        bookController.getAll()
//        bookController.getBookByTitle(CONST_BOOK_TITLE)
//        bookController.getStartingWith(CONST_BOOK_TITLE)
    }

    //@Scheduled(fixedRate = 5_000_000_000)
    fun defaultData() {
        defaultDataCreator.createData()
    }

}

const val CONST_BOOK_TITLE = "bla"
private val logger = KLogging().logger