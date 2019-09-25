package com.surya.androidjetpackpro.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.surya.androidjetpackpro.data.models.Movie
import com.surya.androidjetpackpro.data.models.TVShow
import com.surya.androidjetpackpro.data.paging.TvShowDataSourceFactory
import com.surya.androidjetpackpro.data.remote.MyApiService
import com.surya.androidjetpackpro.data.remote.responses.TVShowResponse
import com.surya.androidjetpackpro.data.repositories.AppRepository
import junit.framework.Assert.assertNotNull
import org.junit.*
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

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = TvshowViewModel(repository)
    }

    @After
    fun tearDown(){}

    @Test
    fun getTvShow_test(){

        val dummyData : MutableLiveData<PagedList<TVShow>> = MutableLiveData()

        val pagedList  = Mockito.mock(PagedList::class.java)

        dummyData.value = pagedList as PagedList<TVShow>

        `when`(viewModel?.getTvShow()).thenReturn(dummyData)

        val observer  = Mockito.mock(Observer::class.java) as Observer<PagedList<TVShow>>

        viewModel?.getTvShow()?.observeForever(observer)

        verify(observer).onChanged(pagedList)
        assertNotNull(viewModel?.getTvShow())
    }
}