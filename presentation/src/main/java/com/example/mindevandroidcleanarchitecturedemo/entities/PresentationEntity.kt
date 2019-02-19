package com.example.mindevandroidcleanarchitecturedemo.entities

sealed class PresentationEntity {
    data class HackerNews(
        val comments_count: Int? = 0,
        val id: Int? = 0,
        val time_ago: String? = "",
        val title: String? = ""
    ) : PresentationEntity()
}