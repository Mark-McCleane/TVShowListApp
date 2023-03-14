package com.example.tvshowapp.MainActivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import getOrAwaitValue
import junit.framework.Assert.assertNotNull
import com.example.tvshowapp.MainActivity.model.TVShow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
//    private lateinit var fakeTvShowRepository
    private lateinit var testTvShow: TVShow

    @Before
    fun setUpViewModel(){
//        dao = createDao
//        fakeTvShowRepository = FakeTvShowRepository(dao)
        testTvShow = TVShow("Foo", "01,01,2022", 1, "Foo", "Foo",
            "Foo", "Foo", 1000.00, "Foo", 5.9, 100)
    }


    @Test
    fun addNewTvShow_SetsNewTvShow() {
        val viewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
        viewModel.addTvShow(testTvShow)
        val value = viewModel.getTvShowListAsc().getOrAwaitValue()
        assert(value[0] == testTvShow)
    }

    fun getTvShowListAsc_returnsTvShowList(){
        val viewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
        viewModel.addTvShow(testTvShow)
        assertNotNull(viewModel.getTvShowListAsc())
    }

    fun getTvShowListDes_returnsTvShowList(){
        val viewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
        viewModel.addTvShow(testTvShow)
        assertNotNull(viewModel.getTvShowListDesc())
    }
}