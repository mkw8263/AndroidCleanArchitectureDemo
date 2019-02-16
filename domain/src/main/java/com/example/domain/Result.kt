package com.example.domain

class Result<out T>(private val status: Status, private val data: T?, val throwable: Throwable?) {
    val loading
        get() = status == Status.LOADING
    val error
        get() = status == Status.ERROR
    val success
        get() = status == Status.SUCCESS

    fun getData(): T? = data

    companion object {
        fun <T> loading(data: T?): Result<T> = Result(Status.LOADING, data, null)
        fun <T> error(throwable: Throwable): Result<T> = Result(Status.ERROR, null, throwable)
        fun <T> success(data: T): Result<T> = Result(Status.SUCCESS, data, null)
    }
}