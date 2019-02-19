package com.example.domain.entities

sealed class DomainEntity {
    data class HackerNews(
        val comments_count: Int? = 0,
        val id: Int? = 0,
        val time_ago: String? = "",
        val title: String? = ""
    ) : DomainEntity()
}