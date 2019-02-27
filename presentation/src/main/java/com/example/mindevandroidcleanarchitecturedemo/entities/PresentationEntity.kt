package com.example.mindevandroidcleanarchitecturedemo.entities

sealed class PresentationEntity {
    data class HackerNews(
        var comments_count: Int? = 0,
        var id: Int? = 0,
        var time_ago: String? = "",
        var title: String? = ""
    ) : PresentationEntity()
}