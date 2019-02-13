package com.example.data.repository

import com.example.data.BaseRepository

interface HackerNewsRepository :BaseRepository{

    fun getHackerNewsList()
}