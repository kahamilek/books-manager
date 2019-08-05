package com.aanu.books.manager.books.model

data class BookFromPeriodResponse(
        val status: BookFromPeriodResponseStatus = BookFromPeriodResponseStatus.SUCCESS,
        val books: List<BookWeb>
)