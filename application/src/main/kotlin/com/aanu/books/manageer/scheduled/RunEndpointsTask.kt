package com.aanu.books.manageer.scheduled

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import com.aanu.books.manageer.data.DefaultData
import com.aanu.books.manageer.web.dto.Book
import com.aanu.books.manageer.web.controller.BooksController

@Controller
class RunEndpointsTask(
        private val bookController: BooksController,
        private val defaultData: DefaultData
) {

    val CONST_BOOK = Book(
            title = defaultData.getRandomTitle(),
            size = 5
    )

    @Scheduled(fixedDelay = 5_000)
    fun runAllEndpointsFixed5s() {
        bookController.getAll()
        bookController.getBookByTitle(CONST_BOOK_TITLE)
        bookController.getStartingWith(CONST_BOOK_TITLE)
    }

    @Scheduled(fixedRate = 5_000_000_000)
    fun defaultData() {
        defaultData.createData()
    }

}

const val CONST_BOOK_TITLE = "bla"
