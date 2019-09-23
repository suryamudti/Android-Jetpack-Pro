package com.surya.androidjetpackpro.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.data.repositories.AppRepository
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by suryamudti on 16/09/2019.
 */

@RunWith(JUnit4::class)
class TvshowViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var repository = Mockito.mock(AppRepository::class.java)
    private var viewModel: TvshowViewModel? = null

    @Mock
    private lateinit var response: TVShowResponse

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = TvshowViewModel(repository)
    }

    @After
    fun tearDown(){}

    @Test
    fun getTvShow(){
        val fakeData : MutableLiveData<PagedList<TVShow>> = MutableLiveData()
        val pagedList = Mockito.mock(PagedList::class.java)


        fakeData.value = pagedList as PagedList<TVShow>

        `when`(repository.getRemoteTvShow()).thenReturn(fakeData)
        verify(repository).getRemoteTvShow()
    }
}