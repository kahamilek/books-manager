package com.aanu.books.manageer.web.dto

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "books")
data class Book(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val title: String = "",
        val size: Long = 0,
        val creationDate: LocalDate = LocalDate.now(),
        @Enumerated(EnumType.STRING)
        var status: BookStatus = BookStatus.NONE
)