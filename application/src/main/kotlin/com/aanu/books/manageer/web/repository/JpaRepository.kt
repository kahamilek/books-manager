package com.aanu.books.manageer.web.repository

import com.aanu.books.manageer.web.dto.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface JpaBookRepository : JpaRepository<Book, Long> {
    fun findByTitle(title: String): Book
    fun findByTitleEquals(title: String): Book

    fun findAllByCreationDateBetween(fromInclusive: LocalDate, toInclusive: LocalDate): List<Book>
}