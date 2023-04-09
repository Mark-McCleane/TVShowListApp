package com.example.tvshowapp.MainActivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tvshowapp.MainActivity.utils.Status
import com.example.tvshowapp.MainActivity.model.TVShow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TvShowViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `addTvShow item with correct fields returns success`(){
        val tvShow = TVShow("fads", "12/06/2021", 0, "Test","Test",
            "Test","Test",0.0,"Test", 0.0, 0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.SUCCESS)
    }

    @Test
    fun `addTvShow item with incorrect name return error`(){
        val tvShow = TVShow("fads", "12/06/2021", 0, "","Test",
            "Test","Test",0.0,"Test", 0.0, 0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.ERROR)
    }

    @Test
    fun `addTvShow item with incorrect overview return error`(){
        val tvShow = TVShow("fads", "12/06/2021", 0, "Test","Test",
            "Test","",0.0,"Test", 0.0, 0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.ERROR)
    }

    @Test
    fun `addTvShow item with incorrect posterpath return success`(){
        val tvShow = TVShow("fads", "12/06/2021", 0, "Test","Test",
            "Test","Test",0.0,"", 0.0, 0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.SUCCESS)
    }

    @Test
    fun `addTvShow item with incorrect first air date return success`(){
        val tvShow = TVShow("fads", "", 0, "Test","Test",
            "Test","Test",0.0,"Test", 0.0, 0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.SUCCESS)
    }

    @Test
    fun `addTvShow item with empty fields returns error`() {
        val tvShow: TVShow = TVShow("", "", 0,"","",
            "","",0.0,"",0.0,0)
        viewModel.addTvShow(tvShow)
        val value = viewModel.insertTvShowStatus.getOrAwaitValueTest()
        assert(value.getContentIfNotHandled()?.status == Status.ERROR)
    }
}