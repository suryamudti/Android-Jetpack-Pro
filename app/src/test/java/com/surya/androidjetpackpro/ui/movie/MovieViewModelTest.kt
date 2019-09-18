package com.surya.androidjetpackpro.ui.movie

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.data.repositories.MovieRepository
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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
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
    private var repository = Mockito.mock(MovieRepository::class.java)

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var response: MoviesResponse

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getMovies() = testDispatcher.runBlockingTest {
        GlobalScope.launch {
            `when`(repository?.getMovies()).thenReturn(response)

            val moviesResponse = repository.getMovies()
            verify(repository)?.getMovies()

            assertNotNull(moviesResponse)

            assertTrue(moviesResponse.results.size>1)

        }
    }

}