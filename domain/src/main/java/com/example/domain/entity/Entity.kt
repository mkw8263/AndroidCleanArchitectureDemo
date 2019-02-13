package com.example.domain.entity

sealed class Entity {

    data class HackerNews(
        val comments_count: Int,
        val domain: String,
        val id: Int,
        val points: Int,
        val time: Int,
        val time_ago: String,
        val title: String,
        val type: String,
        val url: String,
        val user: String
    ) : Entity()
}