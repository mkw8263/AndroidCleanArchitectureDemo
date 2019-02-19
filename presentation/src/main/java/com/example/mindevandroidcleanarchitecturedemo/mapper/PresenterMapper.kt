package com.example.mindevandroidcleanarchitecturedemo.mapper

interface PresenterMapper<V, D> {
    fun mapToView(type: V): D
}