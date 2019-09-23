package com.surya.androidjetpackpro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.data.repositories.AppRepository
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

    private val repository = Mockito.mock(AppRepository::class.java)
    private var viewModel: MovieViewModel? = null

    @Mock
    private lateinit var response: MoviesResponse

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(repository)
    }

    @After
    fun tearDown(){}

    @Test
    fun getMovies() {
        val fakeData : MutableLiveData<PagedList<Movie>> = MutableLiveData()
        val pagedList = mock(PagedList::class.java)


        fakeData.value = pagedList as PagedList<Movie>

        `when`(repository.getRemoteMovies()).thenReturn(fakeData)
        verify(repository).getRemoteMovies()
    }

}