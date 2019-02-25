package com.example.mindevandroidcleanarchitecturedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    private lateinit var mainViewModel: MainViewModel
    private lateinit var presentationHackerNewsMapper: PresentationHackerNewsMapper

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
        mainViewModel.mutableLiveResult.postValue(MainViewModel.Result.NewsData(value))
        assertEquals(value, (mainViewModel.liveResult.value as MainViewModel.Result.NewsData).data)
    }

    @Test
    fun `live data, hacker news error`() {
        val value = Throwable(" error ")
        mainViewModel.mutableLiveResult.postValue(MainViewModel.Result.ShowError(value))
        assertEquals(value, (mainViewModel.liveResult.value as MainViewModel.Result.ShowError).throwable)
    }

    @Test
    fun `live data, hacker news loading`() {
        mainViewModel.mutableLiveResult.postValue(MainViewModel.Result.ProgressBarVisibility(true))
        mainViewModel.mutableLiveResult.postValue(MainViewModel.Result.ProgressBarVisibility(false))
        assertEquals(false, (mainViewModel.liveResult.value as MainViewModel.Result.ProgressBarVisibility).isLoading)
    }

    @Test
    fun `ui entity, DomainEntity change Presentation Entity`() {
        val presentationHackerNewsMapper = PresentationHackerNewsMapper()
        val domainEntity = DomainEntity.HackerNews(0, 0, "1", "1")
        println("domainEntity comments_count${domainEntity.comments_count}")
        println("domainEntity id${domainEntity.id}")
        println("domainEntity time_ago${domainEntity.time_ago}")
        println("domainEntity title${domainEntity.title}")

        val entity: PresentationEntity.HackerNews = presentationHackerNewsMapper.mapToView(domainEntity)
        println("changed ")
        println("PresentationEntity comments_count${entity.comments_count}")
        println("PresentationEntity id${entity.id}")
        println("PresentationEntity time_ago${entity.time_ago}")
        println("PresentationEntity title${entity.title}")
    }
}