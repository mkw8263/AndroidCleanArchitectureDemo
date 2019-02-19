package com.example.data.entities

sealed class DataEntity {
    data class HackerNews(
        var comments_count: Int? = 0,
        var domain: String? = "",
        var id: Int? = 0,
        var points: Int? = 0,
        var time: Int? = 0,
        var time_ago: String? = "",
        var title: String? = "",
        var type: String? = "",
        var url: String? = "",
        var user: String? = ""
    ) : DataEntity()
}