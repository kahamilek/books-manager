package com.aanu.books.manageer.web.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Arrays.asList

@Configuration
class CacheConfiguration {

    companion object CACHE_NAMES{
        const val BOOK_BY_TITLE_CACHE_NAME = "bookByTitle"
        const val BOOKS_CACHE_NAME = "books"
    }

    @Bean
    fun cacheConfig(): CacheManager {
        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(asList(
            ConcurrentMapCache("bookByTitle"),
            ConcurrentMapCache("books")
        ))
        return cacheManager
    }
}