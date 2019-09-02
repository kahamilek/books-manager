package com.aanu.books.manager.books.model

import java.time.LocalDate

data class BookWeb(
        val id: Long,
        val title: String,
        val creationDate: LocalDate,
        val size: Int,
        val status: String
)