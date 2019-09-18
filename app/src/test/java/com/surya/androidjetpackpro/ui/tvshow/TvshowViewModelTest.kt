package com.surya.androidjetpackpro.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.surya.androidjetpackpro.data.remote.responses.MoviesResponse
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.data.repositories.MovieRepository
import com.surya.androidjetpackpro.data.repositories.TvShowRepository
import junit.framework.Assert
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
class TvshowViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var repository = Mockito.mock(TvShowRepository::class.java)

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    private lateinit var response: TVShowResponse

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
    fun getTvShow() = testDispatcher.runBlockingTest {
        GlobalScope.launch {
            `when`(repository?.getTVShows()).thenReturn(response)

            val tvShowResponse = repository.getTVShows()
            verify(repository)?.getTVShows()

            assertNotNull(tvShowResponse)

            assertTrue(tvShowResponse.results.size > 1)
        }
    }
}