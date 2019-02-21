package com.example.mindevandroidcleanarchitecturedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.Result
import com.example.domain.entities.DomainEntity
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HackerNewsVMTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel
    lateinit var presentationHackerNewsMapper: PresentationHackerNewsMapper

    @Mock
    lateinit var hackerNewsUseCase: HackerNewsUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presentationHackerNewsMapper = PresentationHackerNewsMapper()
        mainViewModel = MainViewModel(hackerNewsUseCase, presentationHackerNewsMapper)
    }

    @Test
    fun `live data, hacker news success`() {
        val value = listOf(PresentationEntity.HackerNews(0, 0, "1", "1"))
        mainViewModel.hackerNewsLiveData.postValue(Result.success(value))
        assertEquals(value, mainViewModel.hackerNewsLiveData.value?.getData())
    }

    @Test
    fun `live data, hacker news error`() {
        val value = Throwable(" error ")
        mainViewModel.errorLiveData.postValue(Result.error(value))
        assertEquals(value, mainViewModel.errorLiveData.value?.throwable)
    }

    @Test
    fun `live data, hacker news loading`() {
        mainViewModel.loadingLiveData.postValue(true)
        mainViewModel.loadingLiveData.postValue(false)
        assertEquals(false, mainViewModel.loadingLiveData.value)
    }

    @Test
    fun `DomainEntity change Presentation Entity`() {
        val presentationHackerNewsMapper = PresentationHackerNewsMapper()
        val domainEntity = DomainEntity.HackerNews(0, 0, "1", "1")
        println("domainEntity comments_count${domainEntity.comments_count}")
        println("domainEntity id${domainEntity.id}")
        println("domainEntity time_ago${domainEntity.time_ago}")
        println("domainEntity title${domainEntity.title}")

        val entity: PresentationEntity.HackerNews =
            presentationHackerNewsMapper.mapToView(domainEntity)
        println("changed ")
        println("PresentationEntity comments_count${entity.comments_count}")
        println("PresentationEntity id${entity.id}")
        println("PresentationEntity time_ago${entity.time_ago}")
        println("PresentationEntity title${entity.title}")
    }
}