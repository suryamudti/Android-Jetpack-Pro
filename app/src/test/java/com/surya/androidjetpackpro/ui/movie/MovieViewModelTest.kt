package com.surya.androidjetpackpro.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.data.repositories.MovieRepository
import com.surya.androidjetpackpro.utils.Constants
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
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
import org.mockito.MockitoAnnotations

/**
 * Created by suryamudti on 16/09/2019.
 */

@RunWith(JUnit4::class)
class MovieViewModelTest{

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var repository = Mockito.mock(MovieRepository::class.java)

    @Mock
    private lateinit var viewModel: MovieViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var response: MoviesResponse

    @Before
    fun setup(){

        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)
        viewModel = MovieViewModel(repository)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getMovies()  = testDispatcher.runBlockingTest {
        MainScope().launch {
            Mockito.`when`(repository?.getMovies()).thenReturn(response)
//            Mockito.verify(viewModel)?.getMovies(Constants.DEFAULT)
        }



    }

}