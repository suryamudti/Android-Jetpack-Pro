package com.surya.androidjetpackpro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.paging.MovieDataSourceFactory
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.data.repositories.AppRepository
import com.surya.androidjetpackpro.ui.utils.mockPagedList
import com.surya.androidjetpackpro.utils.Resource
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by suryamudti on 16/09/2019.
 */

@RunWith(JUnit4::class)
class MovieViewModelTest{

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val repository = mock(AppRepository::class.java)

    @Mock
    private var viewModel: MovieViewModel? = null

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(repository)
    }

    @After
    fun tearDown(){}

    @Test
    fun getMovies_test(){
        val dummyData : MutableLiveData<PagedList<Movie>> = MutableLiveData()

        val pagedList  = mock(PagedList::class.java)

        dummyData.value = pagedList as PagedList<Movie>

        `when`(viewModel?.getMovies()).thenReturn(dummyData)

        val observer  = mock(Observer::class.java) as Observer<PagedList<Movie>>

        viewModel?.getMovies()?.observeForever(observer)

        verify(observer).onChanged(pagedList)
        assertNotNull(viewModel?.getMovies())
    }

}