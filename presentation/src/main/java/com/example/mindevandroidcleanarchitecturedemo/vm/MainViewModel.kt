package com.example.mindevandroidcleanarchitecturedemo.vm

import com.example.domain.entity.Entity
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.base.MindevViewModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class MainViewModel @Inject constructor(private val hackerNewsUseCase: HackerNewsUseCase) : MindevViewModel() {


    sealed class UiEvent {

    }

    sealed class Action {
        object Initialize : Action()
    }

    sealed class Result {
        data class HackerNewsList(val items: List<Entity.HackerNews>) : Result()
        data class ProgressBarStatus(val isLoading: Boolean) : Result()
    }


    private val uiEventSubject: PublishSubject<UiEvent> = PublishSubject.create()
    private val actionSubject: PublishSubject<Action> = PublishSubject.create()
    private val resultSubject: PublishSubject<Result> = PublishSubject.create()

    private fun dispatchResult(result: Result) {
        resultSubject.takeIf { !it.hasComplete() }?.onNext(result)
    }

    fun run(): Observable<Result> {
        val action = uiEventSubject.compose(uiEventToAction()).startWith(Observable.just(Action.Initialize))
        val signal = Observable.merge(action, actionSubject).compose(actionToResult())
        return Observable.merge(signal, resultSubject).observeOn(AndroidSchedulers.mainThread())
    }


    private fun uiEventToAction(): ObservableTransformer<UiEvent, Action> {
        return ObservableTransformer { upstream ->
            upstream.flatMap { uiEvent ->
                when (uiEvent) {

                }
                return@flatMap Observable.empty<Action>()
            }
        }
    }

    private fun actionToResult(): ObservableTransformer<Action, Result> {
        return ObservableTransformer { upstream ->
            upstream.flatMap { actionEvent ->
                when (actionEvent) {
                    is Action.Initialize -> return@flatMap getHackerNewsList()
                }
            }
        }
    }

    private fun getHackerNewsList(): Observable<Result> {
        dispatchResult(Result.ProgressBarStatus(true))
        return hackerNewsUseCase.execute(HackerNewsUseCase.Param(30)).toObservable()
            .map {
                dispatchResult(Result.ProgressBarStatus(false))
                Result.HackerNewsList(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        uiEventSubject.onComplete()
        actionSubject.onComplete()
        resultSubject.onComplete()
    }
}