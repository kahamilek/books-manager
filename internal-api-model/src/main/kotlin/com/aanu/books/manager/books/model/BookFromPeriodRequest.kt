package com.aanu.books.manager.books.model

import java.time.LocalDate

data class BookFromPeriodRequest(
        val fromDateInclusive: LocalDate,
        val throughDateInclusive: LocalDate
)