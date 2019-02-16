package com.example.domain.entity

sealed class Entity {

    data class HackerNews(
        val comments_count: Int? = 0,
        val domain: String? = "",
        val id: Int? = 0,
        val points: Int? = 0,
        val time: Int? = 0,
        val time_ago: String? = "",
        val title: String? = "",
        val type: String? = "",
        val url: String? = "",
        val user: String? = ""
    ) : Entity()
}
//data class 1(
//    val comments_count: Int,
//    val domain: String,
//    val id: Int,
//    val points: Int,
//    val time: Int,
//    val time_ago: String,
//    val title: String,
//    val type: String,
//    val url: String,
//    val user: String
//)
//data class zz(
//    val comment
//    2019-02-16 22:42:38.650 8808-8946/com.example.mindevandroidcleanarchitecturedemo D/OkHttp: s_count: Int,
//val comments_count: Int,
//val domain: String,
//val id: Int,
//val points: Int,
//val time: Int,
//val time_ago: String,
//val title: String,
//val type: String,
//val url: String,
//val user: String
//)