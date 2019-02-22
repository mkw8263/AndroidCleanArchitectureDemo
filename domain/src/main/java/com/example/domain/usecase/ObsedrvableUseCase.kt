package com.example.domain.usecase

import io.reactivex.Observable

interface ObservableUseCase<Params, Type> : BaseUseCase {
    fun execute(params: Params?): Observable<Type>
}
