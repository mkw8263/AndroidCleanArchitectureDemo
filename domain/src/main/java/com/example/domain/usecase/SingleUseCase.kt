package com.example.domain.usecase

import io.reactivex.Single

interface SingleUseCase<Params, Type> : BaseUseCase {
    fun execute(params: Params?): Single<Type>
}