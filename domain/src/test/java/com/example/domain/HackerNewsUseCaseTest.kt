package com.example.domain

import com.example.domain.entities.DomainEntity
import com.example.domain.usecase.news.HackerNewsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito.`when`

class HackerNewsUseCaseTest {
    @Test
    fun `hacker news, useCase execute`() {
        val mock = mock<HackerNewsUseCase>()
        val list = ArrayList<DomainEntity.HackerNews>()
        list.apply {
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
        }
        `when`(mock.execute(HackerNewsUseCase.Param(3))).thenReturn(Single.just(list))

        mock.execute(HackerNewsUseCase.Param(3))
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(list)

        verify(mock).execute(HackerNewsUseCase.Param(3))
    }
}